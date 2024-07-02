package eu.urbanage.GeoDataExtractor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MultiPointRadius {

    @JsonProperty("multipoint")
    List<PointRadiusFeature> multipoint;

    @JsonProperty("filter")
    private List<String> filter;

    @JsonProperty("subfilter")
    private List<List<String>> subfilter;

    @JsonProperty("city")
    private String cityName;

    public List<PointRadiusFeature> getMultipoint() {
        return multipoint;
    }

    public void setMultipoint(List<PointRadiusFeature> multipoint) {
        this.multipoint = multipoint;
    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<List<String>> getSubfilter() {
        return subfilter;
    }

    public void setSubfilter(List<List<String>> subfilter) {
        this.subfilter = subfilter;
    }
}
