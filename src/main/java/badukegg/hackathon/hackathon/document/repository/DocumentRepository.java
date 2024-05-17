package badukegg.hackathon.hackathon.document.repository;

import badukegg.hackathon.hackathon.document.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
