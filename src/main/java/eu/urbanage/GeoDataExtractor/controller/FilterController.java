package eu.urbanage.GeoDataExtractor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import eu.urbanage.GeoDataExtractor.model.Filter;
import eu.urbanage.GeoDataExtractor.service.FilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "https://geodata-extractor-ui.dev.ecosystem-urbanage.eu")
@RestController
@RequestMapping("/api/filter")
public class FilterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    @PostMapping("/")
    public ResponseEntity<String> postFilter(@RequestBody String filterJson) {

        LOGGER.info("Received filter request: " + filterJson);

        try {
            // Converting the JSON string back to a Filter object
            ObjectMapper objectMapper = new ObjectMapper();
            Filter filter = objectMapper.readValue(filterJson, Filter.class);

            FilterService fs = new FilterService(new String("orion.ecosystem-urbanage.eu"), new String("443"));

            List<String> test = fs.getAllCityFilter(filter.getCityName());

            return ResponseEntity.ok().body(test.toString());

        } catch (JsonProcessingException e) {
            LOGGER.error("Error processing filter JSON", e);
            return ResponseEntity.badRequest().body("Error processing filter JSON");
        }
    }
}
