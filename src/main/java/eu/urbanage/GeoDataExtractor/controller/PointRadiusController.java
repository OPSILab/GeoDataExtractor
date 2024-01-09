package eu.urbanage.GeoDataExtractor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.urbanage.GeoDataExtractor.model.PointRadius;
import eu.urbanage.GeoDataExtractor.service.GeojsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = {"https://geodata-extractor-ui.dev.ecosystem-urbanage.eu", "https://geodata-extractor-ui.ecosystem-urbanage.eu", "https://gisviewer.santander.dev.ecosystem-urbanage.eu", "https://gisviewer.santander.ecosystem-urbanage.eu", "http://localhost:4200"})
@RestController
@RequestMapping("/api/pointradiusdata")
@Deprecated
public class PointRadiusController {


    @Autowired
    private GeojsonService gs;

    private static final Logger LOGGER = LoggerFactory.getLogger(PointRadiusController.class);

    @PostMapping("/")
    public ResponseEntity<String> postPointRadius(@RequestBody PointRadius pointrad) throws JsonProcessingException {

        List<String> test = gs.getFromPointRadius(pointrad);

        return ResponseEntity.ok().body(test.toString());

    }

}
