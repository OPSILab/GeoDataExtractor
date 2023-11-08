package eu.urbanage.GeoDataExtractor.service;

import eu.urbanage.GeoDataExtractor.model.Document;
import eu.urbanage.GeoDataExtractor.model.GeoJSONFeature;
import eu.urbanage.GeoDataExtractor.repository.ConfigRepository;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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


    public ResponseEntity<GeoJSONFeature> findDocumentGeojson(String id) {

        try {

            Optional<Document> foundDocument = dRepo.findById(id);


            if (foundDocument.isPresent()) {

                return ResponseEntity.ok().body(foundDocument.get().getGeojson());
            }
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            log.error("errore durante il reperimento dei dati {} a causa di: {}", id, e.getMessage());
            return null;
        }
    }
    public Document findDocumentObj(String id) {

        try {

            Optional<Document> foundDocument = dRepo.findById(id);


            if (foundDocument.isPresent()) {
                return foundDocument.get();
            }
            return null;

        } catch (Exception e) {
            log.error("errore durante il reperimento dei dati {} a causa di: {}", id, e.getMessage());
            return null;
        }
    }


    public ResponseEntity<List<Document>> findAllDocumentOfUser(String userID) {

        try {

            List<Document> foundDocuments = dRepo.findByUserID(userID);

            if (foundDocuments.isEmpty()) {

                List<Document> emptyList = Collections.emptyList();

                return ResponseEntity.ok(emptyList);
            }

            return ResponseEntity.ok().body(foundDocuments);

        } catch (Exception e) {
            log.error("errore durante il reperimento dei dati");
            return null;
        }
    }



    public ResponseEntity<List<Document>> findAllDocumentByCity(String city) {

        try {

            List<Document> foundDocuments = dRepo.findBycityName(city);


            return ResponseEntity.ok().body(foundDocuments);

        } catch (Exception e) {
            log.error("errore durante il reperimento dei dati");
            return null;
        }
    }





    public ResponseEntity<List<Document>> findAllDocument() {

        try {

            List<Document> foundDocuments = dRepo.findAll();


            return ResponseEntity.ok().body(foundDocuments);

        } catch (Exception e) {
            log.error("errore durante il reperimento dei dati");
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

    public ResponseEntity<Document> updateDocument(Document doc) {

        String id = doc.getId();

        try {

            Optional<Document> foundDocument = dRepo.findById(id);


            if (foundDocument.isPresent()) {

                dRepo.save(doc);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            log.error("errore durante il reperimento dei dati {} a causa di: {}", id, e.getMessage());
            return null;
        }
    }



}
