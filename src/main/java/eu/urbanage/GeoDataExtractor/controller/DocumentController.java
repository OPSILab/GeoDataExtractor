package eu.urbanage.GeoDataExtractor.controller;

import eu.urbanage.GeoDataExtractor.model.Document;
import eu.urbanage.GeoDataExtractor.repository.ConfigRepository;
import eu.urbanage.GeoDataExtractor.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/document")
public class DocumentController {


    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService ds;

    @PostMapping("/save/")
    public String postDocument(@RequestBody Document docJson) {

        LOGGER.info("Received document store: " + docJson.getName());

        ds.addDocument(docJson);

        return docJson.getId();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable() String id) {


        try {

            return ds.findDocument(id);


        } catch (Exception e) {
            LOGGER.error(id, e);
            return ResponseEntity.internalServerError().body(null);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable() String id) {


        try {

            return ds.deleteDocument(id);


        } catch (Exception e) {
            LOGGER.error(id, e);
            return ResponseEntity.internalServerError().body(null);
        }

    }
}
