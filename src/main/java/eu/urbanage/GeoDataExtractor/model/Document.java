package eu.urbanage.GeoDataExtractor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.core.util.Json;
import org.bson.json.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
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
    private Object geojson;

    @JsonProperty("layers")
    private List<Object> layers;

    private Date dateCreation;

    private String userEmail;

    private String userID;

    private String cron_id;

    public List<Object> getLayers() {
        return layers;
    }

    public void setLayers(List<Object> layers) {
        this.layers = layers;
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

    public Object getGeojson() {
        return geojson;
    }

    public void setGeojson(Object geojson) {
        this.geojson = geojson;
    }

    public String getCron_id() {
        return cron_id;
    }

    public void setCron_id(String cron_id) {
        this.cron_id = cron_id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}


