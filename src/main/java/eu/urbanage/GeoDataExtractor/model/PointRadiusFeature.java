package eu.urbanage.GeoDataExtractor.model;


public class PointRadiusFeature {

    private Coordinates point;


    private String radius;

    private boolean external;

    public Coordinates getPoint() {
        return point;
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

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }
}
