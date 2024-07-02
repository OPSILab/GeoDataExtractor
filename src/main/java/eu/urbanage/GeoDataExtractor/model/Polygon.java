package eu.urbanage.GeoDataExtractor.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import static java.lang.String.valueOf;

public class Polygon {

    public Polygon() {
    }

    public Polygon(List<Coordinates> polygon, List<String> filter, String cityName) {
        this.polygon = polygon;
        this.filter = filter;
        this.cityName = cityName;
    }

    @JsonProperty("polygon")
    private List<Coordinates> polygon;

    @JsonProperty("filter")
    private List<String> filter;

    @JsonProperty("city")
    private String cityName;


    public List<Coordinates> getPolygon() {
        return polygon;
    }


    public String getPolygonString(){
        StringBuilder polystring = new StringBuilder("[[");

        for (Coordinates cord : polygon){

            polystring.append("[").append(valueOf(cord.getLongitude())).append(",").append(valueOf(cord.getLatitude())).append("],");

        }

        polystring.deleteCharAt(polystring.length() - 1);
        polystring.append("]]");

        return polystring.toString();

    }

    public void setPolygon(List<Coordinates> polygon) {
        this.polygon = polygon;
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
