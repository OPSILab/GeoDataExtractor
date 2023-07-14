package eu.urbanage.GeoDataExtractor.controller;


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

import java.util.List;


@RestController
@RequestMapping("/api/filter")
public class FilterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    @PostMapping("/")
    public ResponseEntity<String> postFilter(@RequestBody Filter filter) throws JsonProcessingException {

        LOGGER.info("Filter list sent");

        FilterService fs = new FilterService(new String("localhost"), new String("1026"));


        List<String> test = fs.getAllCityFilter(filter.getCityName());

        return ResponseEntity.ok().body(test.toString());

    }

}
