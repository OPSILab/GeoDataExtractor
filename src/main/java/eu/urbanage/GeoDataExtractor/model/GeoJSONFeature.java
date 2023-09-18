package eu.urbanage.GeoDataExtractor.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class GeoJSONFeature {

    private String type;
    private Map<String, Object> properties = new HashMap<>();


    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("properties")
    public Map<String, Object> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void addProperty(String key, Object value) {
        properties.put(key, value);
    }



    // Optional: Additional methods or fields as needed

    @Override
    public String toString() {
        return "GeoJSONFeature{" +
                "type='" + type + '\'' +
                ", properties=" + properties +
                '}';
    }

    // Define any additional classes for Geometry if needed
    // Example class for Point geometry:
    // public class Point {
    //    private String type = "Point";
    //    private double[] coordinates;
    //    // Getter and setter methods for coordinates
    //    // ...
    // }
}