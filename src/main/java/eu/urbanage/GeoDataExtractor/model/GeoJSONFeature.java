package eu.urbanage.GeoDataExtractor.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoJSONFeature {

    private String type;
    private List<Object> features = new ArrayList<>();


    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("features")
    public Object getProperties() {
        return features;
    }

    @JsonAnySetter
    public void setFeatures(List<Object> features) {
        this.features = features;
    }



}