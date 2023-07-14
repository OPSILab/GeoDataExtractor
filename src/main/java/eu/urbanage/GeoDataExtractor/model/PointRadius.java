package eu.urbanage.GeoDataExtractor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PointRadius {

    @JsonProperty("point")
    private Coordinates point;


    @JsonProperty("radius")
    private String radius;


    @JsonProperty("filter")
    private List<String> filter;


    @JsonProperty("city")
    private String cityName;
}
