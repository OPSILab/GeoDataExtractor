package eu.urbanage.GeoDataExtractor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static java.lang.String.valueOf;

public class MultiPolygon {

    @JsonProperty("multiPolygon")
    private List<List<Coordinates>> multiPolygon;

    @JsonProperty("filter")
    private List<String> filter;

    @JsonProperty("subfilter")
    private List<List<String>> subfilter;

    @JsonProperty("city")
    private String cityName;

    public List<List<Coordinates>> getMultiPolygon() {
        return multiPolygon;
    }

    public String getMultiPolygonString() {
        StringBuilder multiPolyString = new StringBuilder("[");

        for (List<Coordinates> polygon : multiPolygon) {
            multiPolyString.append("[");

            for (Coordinates cord : polygon) {

                multiPolyString.append("[").append(valueOf(cord.getLongitude())).append(",")
                        .append(valueOf(cord.getLatitude())).append("],");

            }
            multiPolyString.deleteCharAt(multiPolyString.length() - 1);
            multiPolyString.append("]");
        }

        multiPolyString.deleteCharAt(multiPolyString.length() - 1);
        multiPolyString.append("]");

        return multiPolyString.toString();

    }

    public void setPolygon(List<List<Coordinates>> multiPolygon) {
        this.multiPolygon = multiPolygon;
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

    public void setMultiPolygon(List<List<Coordinates>> multiPolygon) {
        this.multiPolygon = multiPolygon;
    }

    public List<List<String>> getSubfilter() {
        return subfilter;
    }

    public void setSubfilter(List<List<String>> subfilter) {
        this.subfilter = subfilter;
    }
}
