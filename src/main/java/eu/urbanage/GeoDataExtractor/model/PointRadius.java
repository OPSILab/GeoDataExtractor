package eu.urbanage.GeoDataExtractor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static java.lang.String.valueOf;

public class PointRadius {

    @JsonProperty("point")
    private Coordinates point;

    @JsonProperty("radius")
    private String radius;

    @JsonProperty("filter")
    private List<String> filter;

    @JsonProperty("city")
    private String cityName;

    @JsonProperty("external")
    private boolean external;

    public PointRadius(Coordinates point, String radius, List<String> filter, String cityName, boolean external) {
        this.point = point;
        this.radius = radius;
        this.filter = filter;
        this.cityName = cityName;
        this.external = external;
    }

    public PointRadius() {
    }

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public Coordinates getPoint() {
        return point;
    }

    public String getPointString() {
        String coordinates = valueOf(point.getLongitude()) + "," + valueOf(point.getLatitude());
        return coordinates;
    }

    public void setPoint(Coordinates point) {
        this.point = point;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
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
