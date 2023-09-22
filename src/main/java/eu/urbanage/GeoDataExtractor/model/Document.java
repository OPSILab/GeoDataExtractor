package eu.urbanage.GeoDataExtractor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.core.util.Json;
import org.bson.json.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


public class Document {


    @Id
    @JsonProperty()
    private String id;

    @JsonProperty("city")
    private String cityName;


    @JsonProperty("filter")
    private List<String> filter;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;


    @JsonProperty("geojson")
    private GeoJSONFeature geojson;

    @JsonProperty("query")
    private String query;


    private String userEmail;

    private String userID;




    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        this.query = query;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeoJSONFeature getGeojson() {
        return geojson;
    }

    public void setGeojson(GeoJSONFeature geojson) {
        this.geojson = geojson;
    }
}
