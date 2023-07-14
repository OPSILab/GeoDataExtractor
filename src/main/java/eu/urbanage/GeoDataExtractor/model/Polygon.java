package eu.urbanage.GeoDataExtractor.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Polygon {

    @JsonProperty("polygon")
    private List<Coordinates> polygon;

    @JsonProperty("filter")
    private List<String> filter;

    @JsonProperty("city")
    private String cityName;

}
