package eu.urbanage.GeoDataExtractor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import eu.urbanage.GeoDataExtractor.model.Filter;
import eu.urbanage.GeoDataExtractor.model.Polygon;
import eu.urbanage.GeoDataExtractor.service.FilterService;
import eu.urbanage.GeoDataExtractor.service.GeojsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = {"https://geodata-extractor-ui.dev.ecosystem-urbanage.eu", "https://geodata-extractor-ui.ecosystem-urbanage.eu", "https://gisviewer.santander.dev.ecosystem-urbanage.eu", "https://gisviewer.santander.ecosystem-urbanage.eu", "http://localhost:4200"})
@RestController
@RequestMapping("/api/polygondata")
@Deprecated
public class PolygonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolygonController.class);

    @PostMapping("/")
    public ResponseEntity<String> postPolygon(@RequestBody String polygonJson) {

        //LOGGER.info("Received polygon request: " + polygonJson);

        try {
            // Converting the JSON string back to a Polygon object
            ObjectMapper objectMapper = new ObjectMapper();
            Polygon polygon = objectMapper.readValue(polygonJson, Polygon.class);
            
            GeojsonService gs = new GeojsonService();
    
            List<String> test = gs.getFromPolygon(polygon);
    
            return ResponseEntity.ok().body(test.toString());

        } catch (JsonProcessingException e) {
            LOGGER.error("Error processing polygon JSON", e);
            return ResponseEntity.badRequest().body("Error processing polygon JSON");
        }
    }

}
