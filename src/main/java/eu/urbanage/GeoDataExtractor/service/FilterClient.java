package eu.urbanage.GeoDataExtractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface FilterClient {

    List<String> getAllCityFilter(String city) throws JsonProcessingException;

}
