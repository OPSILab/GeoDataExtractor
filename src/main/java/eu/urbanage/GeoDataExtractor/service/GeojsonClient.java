package eu.urbanage.GeoDataExtractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.urbanage.GeoDataExtractor.model.MultiPointRadius;
import eu.urbanage.GeoDataExtractor.model.MultiPolygon;

import java.util.List;

public interface GeojsonClient {

    List<String> getFromMultiPolygon(MultiPolygon data) throws JsonProcessingException;;

    List<String> getFromMultiPointRadius(MultiPointRadius data) throws JsonProcessingException;
}
