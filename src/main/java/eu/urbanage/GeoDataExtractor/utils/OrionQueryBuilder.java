package eu.urbanage.GeoDataExtractor.utils;

public class OrionQueryBuilder {

    private StringBuilder urlBuilder;

    public OrionQueryBuilder(String baseURL) {
        this.urlBuilder = new StringBuilder(baseURL);
    }

    public OrionQueryBuilder() {
        this.urlBuilder = new StringBuilder("https://orion.ecosystem-urbanage.eu/ngsi-ld/v1/entities?limit=1000");
    }

    public OrionQueryBuilder addIdPattern(String type, String city) {
        String appendPattern = "&idPattern=urn:ngsi-ld:" + type + ":" + city + ":*";
        urlBuilder.append(appendPattern);
        return this;
    }

    public OrionQueryBuilder addIdPattern(String type, String city, String element) {
        String appendPattern = "&idPattern=urn:ngsi-ld:" + type + ":" + city + ":" + element + "*";
        urlBuilder.append(appendPattern);
        return this;
    }

    public OrionQueryBuilder addConcise() {
        urlBuilder.append("&options=concise");
        return this;
    }

    public OrionQueryBuilder addFilterQuery(String key, String value) {
        String appendQuery = "&q=" + key + "~=" + value;
        urlBuilder.append(appendQuery);
        return this;
    }


    //&georel=near;" + distanceType + ":" + innerPointRadius.getRadius() + "&geometry=Point&options=concise&coords=" + innerPointRadius.getPointString() +
    public OrionQueryBuilder addPointRadiusQuery(String distanceType, String radius, String pointString) {
        String appendQuery = "&georel=near;" + distanceType + ":" + radius + "&geometry=Point&options=concise&coords=" + pointString;
        urlBuilder.append(appendQuery);
        return this;
    }


    //&georel=intersects&coords=" + innerPolygon.getPolygonString() + "&geometry=Polygon
    public OrionQueryBuilder addPolygonQuery(String polygonString) {
        String appendQuery = "&georel=intersects&coords=" + polygonString + "&geometry=Polygon";
        urlBuilder.append(appendQuery);
        return this;
    }

    public String get() {
        return urlBuilder.toString();
    }
}
