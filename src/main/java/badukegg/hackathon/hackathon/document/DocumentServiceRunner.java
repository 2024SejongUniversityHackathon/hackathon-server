package badukegg.hackathon.hackathon.document;

import badukegg.hackathon.hackathon.document.service.DocumentService;
import badukegg.hackathon.hackathon.document.service.MultipartFileFromPath;
import badukegg.hackathon.hackathon.document.service.MultipartFileFromUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Component
public class DocumentServiceRunner implements CommandLineRunner {

    @Autowired
    private DocumentService documentService;

    @Override
    public void run(String... args) throws IOException {
        String filePath = "/Users/yerong/Downloads/정부24 - 문서출력 (1).pdf";

        // PDF 파일을 URL로부터 MultipartFile로 변환
        MultipartFile file = convertFileToMultipartFile(filePath);

        documentService.savePdfToDb(file);
        documentService.sendPdfToModel(file);
        // PDF에서 텍스트 추출
     //   String extractedText = documentService.extractTextFromPDF(file);
     //   String txtFilePath = documentService.saveTextAsTXT(extractedText);


    }
    private MultipartFile convertUrlToMultipartFile(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        return new MultipartFileFromUrl(url);
    }
    private MultipartFile convertFileToMultipartFile(String filePath) throws IOException {
        File file = new File(filePath);
        return new MultipartFileFromPath(file);
    }
}
