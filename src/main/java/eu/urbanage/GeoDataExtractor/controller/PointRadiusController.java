package eu.urbanage.GeoDataExtractor.controller;

import eu.urbanage.GeoDataExtractor.model.PointRadius;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pointradiusdata")
public class PointRadiusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointRadiusController.class);

    @PostMapping("/")
    public ResponseEntity<PointRadius> postPolygon(@RequestBody PointRadius pointrad) {

        return ResponseEntity.ok().body(pointrad);

    }

}
