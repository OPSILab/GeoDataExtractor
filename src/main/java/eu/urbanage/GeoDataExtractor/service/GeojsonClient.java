package eu.urbanage.GeoDataExtractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.urbanage.GeoDataExtractor.model.MultiPointRadius;
import eu.urbanage.GeoDataExtractor.model.MultiPolygon;
import eu.urbanage.GeoDataExtractor.model.PointRadius;
import eu.urbanage.GeoDataExtractor.model.Polygon;

import java.util.List;

public interface GeojsonClient {

    @Deprecated
    List<String> getFromPolygon(Polygon data) throws JsonProcessingException;


    List<String> getFromMultiPolygon(MultiPolygon data) throws JsonProcessingException;;

    @Deprecated
    List<String> getFromPointRadius(PointRadius data) throws JsonProcessingException;

    List<String> getFromMultiPointRadius(MultiPointRadius data) throws JsonProcessingException;
}
