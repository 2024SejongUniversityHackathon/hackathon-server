package badukegg.hackathon.hackathon.document.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface DocumentService {
   // String extractTextFromPDF(MultipartFile file);
   // String saveTextAsTXT(String text);
    String savePdfToDb(MultipartFile file);
    void sendPdfToModel(MultipartFile file);

}
