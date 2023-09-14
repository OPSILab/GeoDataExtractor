package eu.urbanage.GeoDataExtractor.service;

import eu.urbanage.GeoDataExtractor.model.Document;
import eu.urbanage.GeoDataExtractor.repository.ConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentService {


    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);


    @Autowired
    protected ConfigRepository dRepo;


    public ResponseEntity<Document> findDocument(String id) {

        try {

            Optional<Document> foundDocument = dRepo.findById(id);


            if (foundDocument.isPresent()) {
                return ResponseEntity.ok().body(foundDocument.get());
            }
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            log.error("errore durante il reperimento dei dati {} a causa di: {}", id, e.getMessage());
            return null;
        }
    }


    public ResponseEntity<String> deleteDocument(String id) {

        try {

            dRepo.deleteById(id);

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    public Document addDocument(Document doc) {

        try {

            return dRepo.save(doc);

        } catch (Exception e) {
            log.error("errore durante il salvataggio {} a causa di: {}", doc.getName(),
                    e.getMessage());
            return null;
        }
    }




}
