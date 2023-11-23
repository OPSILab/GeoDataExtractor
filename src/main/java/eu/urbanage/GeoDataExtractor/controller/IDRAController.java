package eu.urbanage.GeoDataExtractor.controller;

import eu.urbanage.GeoDataExtractor.model.Cron;
import eu.urbanage.GeoDataExtractor.model.Document;
import eu.urbanage.GeoDataExtractor.service.CronService;
import eu.urbanage.GeoDataExtractor.service.DocumentService;
import eu.urbanage.GeoDataExtractor.service.IDRAService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = {"https://geodata-extractor-ui.dev.ecosystem-urbanage.eu", "https://geodata-extractor-ui.ecosystem-urbanage.eu", "https://gisviewer.santander.dev.ecosystem-urbanage.eu", "https://gisviewer.santander.ecosystem-urbanage.eu"})
@RestController
@RequestMapping("/api/idra/")
public class IDRAController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    protected DocumentService ds;

    @Autowired
    protected  IDRAService is;

    @Autowired
    private HttpServletRequest request;




    @GetMapping("/{id}")
    public ResponseEntity<String> getCron(@PathVariable() String id) {

        try {

            Document selDoc = ds.findDocument(id).getBody();

            is.postOnIDRA(selDoc);

            return ResponseEntity.ok(id);

        } catch (Exception e) {
            LOGGER.error(id, e);
            return ResponseEntity.internalServerError().body(null);
        }


    }




}
