package badukegg.hackathon.hackathon.document.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;

    @Builder
    public Document (String fileName, String filePath){
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
