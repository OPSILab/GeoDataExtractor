package eu.urbanage.GeoDataExtractor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static java.lang.String.valueOf;

public class MultiPointRadius {

    @JsonProperty("multipoint")
    List<PointRadiusFeature> multipoint;

    @JsonProperty("filter")
    private List<String> filter;

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
}
