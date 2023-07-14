package eu.urbanage.GeoDataExtractor.controller;

import eu.urbanage.GeoDataExtractor.model.Polygon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polygondata")
public class PolygonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolygonController.class);
    @PostMapping("/")
    public ResponseEntity<Polygon> postPolygon(@RequestBody Polygon polygon) {


        return ResponseEntity.ok().body(polygon);

    }

}
