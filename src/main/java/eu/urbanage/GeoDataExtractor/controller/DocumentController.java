package eu.urbanage.GeoDataExtractor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.urbanage.GeoDataExtractor.model.Document;
import eu.urbanage.GeoDataExtractor.service.DocumentService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;



@CrossOrigin(origins = "https://geodata-extractor-ui.dev.ecosystem-urbanage.eu")
@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService ds;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/save/")
    public String postDocument(@RequestBody Document docJson) {

        LOGGER.info("Received document store: " + docJson.getName());

        JsonNode userInfo = decodeUserTokenToJson(getAuthToken());

        docJson.setUserID(String.valueOf(userInfo.get("sub")));

        docJson.setUserEmail(String.valueOf(userInfo.get("email")));

        ds.addDocument(docJson);

        return docJson.getId();

    }

    @PostMapping("/update/")
    public ResponseEntity<Document>  updateDocument(@RequestBody Document docJson) {

        LOGGER.info("Received document update: " + docJson.getName());

        return ds.updateDocument(docJson);

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


    @GetMapping("/getdocuments")
    public ResponseEntity<List<Document>> getAllDocument() {

        try {

            JsonNode userInfo = decodeUserTokenToJson(getAuthToken());

            return ds.findAllDocumentOfUser(String.valueOf(userInfo.get("sub")));

        } catch (Exception e) {
            LOGGER.error(String.valueOf(e));

            return ResponseEntity.internalServerError().body(null);

        }

    }


    @GetMapping("/getalldocuments/{city}")
    public ResponseEntity<List<Document>> getAllDocumentByCity(@PathVariable() String city) {

        try {

            return ds.findAllDocumentByCity(city);

        } catch (Exception e) {
            LOGGER.error(String.valueOf(e));
            return ResponseEntity.internalServerError().body(null);
        }

    }


    @GetMapping("/getalldocuments")
    public ResponseEntity<List<Document>> getAllDocuments() {

        try {

            return ds.findAllDocument();

        } catch (Exception e) {
            LOGGER.error(String.valueOf(e));
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


    private String getAuthToken() {

        return request.getHeader("Authorization");

    }



        public JsonNode decodeUserTokenToJson(String userToken) {

            Base64.Decoder decoder = Base64.getUrlDecoder();

            String[] chunks = userToken.split("\\.");

            String header = new String(decoder.decode(chunks[0]));
            String payload = new String(decoder.decode(chunks[1]));

            ObjectMapper mapper = new ObjectMapper();

            try {
                JsonNode userNode = mapper.readTree(payload);

                return userNode;

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }


        }

}
