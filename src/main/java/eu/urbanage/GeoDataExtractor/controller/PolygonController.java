package eu.urbanage.GeoDataExtractor.controller;

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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/polygondata")
public class PolygonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolygonController.class);
    @PostMapping("/")
    public ResponseEntity<String> postPolygon(@RequestBody Polygon polygon) {

        LOGGER.info("Polygon sent");

        GeojsonService gs = new GeojsonService(new String("orion.dev.ecosystem-urbanage.eu"), new String("443"));

        List<String> test = gs.getFromPolygon(polygon);

        return ResponseEntity.ok().body(test.toString());

    }

}

