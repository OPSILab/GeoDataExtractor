package eu.urbanage.GeoDataExtractor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.urbanage.GeoDataExtractor.model.MultiPointRadius;
import eu.urbanage.GeoDataExtractor.model.MultiPolygon;
import eu.urbanage.GeoDataExtractor.service.GeojsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/multipointradiusdata")
public class MultiPointRadiusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiPointRadiusController.class);

    @PostMapping("/")
    public ResponseEntity<String> postMultiRadius(@RequestBody String pointRadiusJson) {

        try {
            // Converting the JSON string back to a Polygon object
            ObjectMapper objectMapper = new ObjectMapper();
            MultiPointRadius multiPoint = objectMapper.readValue(pointRadiusJson, MultiPointRadius.class);
            
            GeojsonService gs = new GeojsonService(new String("orion.ecosystem-urbanage.eu"), new String("443"));
    
            List<String> test = gs.getFromMultiPointRadius(multiPoint);
    
            return ResponseEntity.ok().body(test.toString());

        } catch (JsonProcessingException e) {
            LOGGER.error("Error processing polygon JSON", e);
            return ResponseEntity.badRequest().body("Error processing polygon JSON");
        }
    }

}