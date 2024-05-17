package badukegg.hackathon.hackathon.document.service.impl;

import badukegg.hackathon.hackathon.common.exception.BaseException;
import badukegg.hackathon.hackathon.common.exception.UserException;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.document.domain.Document;
import badukegg.hackathon.hackathon.document.repository.DocumentRepository;
import badukegg.hackathon.hackathon.document.service.DocumentService;
import badukegg.hackathon.hackathon.member.domain.Member;
import badukegg.hackathon.hackathon.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final MemberRepository memberRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;


    @Override
    public String savePdfToDb( MultipartFile file, String socialId) {
        Member member = memberRepository.findBySocialId(socialId).orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));

        String fileName = file.getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;

        // 파일을 지정된 경로에 저장
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Document document = Document
                .builder()
                        .filePath(filePath)
                                .fileName(fileName)
                                        .member(member).build();
        member.addDocument(document);

        documentRepository.save(document);

        return filePath;
    }

    @Override
    public void sendPdfToModel(MultipartFile pdfFile) {
        // Python 서버 URL
        String pythonServerUrl = "http://localhost:5000/receive_pdf";

        // MultipartFile을 File로 변환
        File convertedFile = convert(pdfFile);

        // 파일 전송을 위한 RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        // 파일을 포함하여 HTTP 요청 생성
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(convertedFile));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Python 서버로 파일 전송 요청
        ResponseEntity<String> response = restTemplate.exchange(pythonServerUrl, HttpMethod.POST, requestEntity, String.class);

        // 응답 확인
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("PDF 파일이 Python 서버로 성공적으로 전송되었습니다.");
        } else {
            log.error("PDF 파일을 Python 서버로 전송하는 데 실패했습니다. 응답 코드: {}", response.getStatusCodeValue());
        }

        // 전송 후 변환된 파일 삭제
        convertedFile.delete();
    }
    private File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("파일 변환 중 오류 발생", e);
        }
        return convFile;
    }

    private static void authorizeMember(Document document) {
        String socialId = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!document.getMember().getSocialId().equals(socialId)){
            throw new BaseException(ResponseCode.FORBIDDEN);
        }
    }
}
