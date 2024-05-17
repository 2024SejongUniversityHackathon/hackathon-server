package badukegg.hackathon.hackathon.document.controller;

import badukegg.hackathon.hackathon.common.response.ApiResponseCustom;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.document.service.DocumentService;
import badukegg.hackathon.hackathon.oauth.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@Tag(name = "Document", description = "Document API")
public class DocumentApiController {

    private final DocumentService documentService;

    @Operation(summary = "문서 가져오기", description = "문서를 가져오는 Controller")
    @ApiResponses(value =  {
            @ApiResponse()})
    @PostMapping("/uploadPdf")
    public ApiResponseCustom<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = documentService.savePdfToDb(file);
            documentService.sendPdfToModel(file);

            return ApiResponseCustom.success(ResponseCode.DOCUMENT_READ_SUCCESS);
        } catch (Exception e) {
            return ApiResponseCustom.fail(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }



}