package eu.urbanage.GeoDataExtractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.urbanage.GeoDataExtractor.model.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IDRAService{

    RestTemplate restTemplate = new RestTemplate();

    public void postOnIDRA(Document postDocument){

        String distributionIDR = "https://ngsi-broker.dev.ecosystem-urbanage.eu/api/distributiondcatap";

        ResponseEntity<String> response;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String downloadURL = "https://geodata-extractor.dev.ecosystem-urbanage.eu/api/document/getGeojson/" + postDocument.getId();

        String jsonBody = createJsonDistribution(postDocument.getId(),postDocument.getCityName(),postDocument.getDescription(), downloadURL).toString();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(distributionIDR, new HttpEntity<>(jsonBody, headers), String.class);

        String datasetId = postDocument.getCityName() + ":" + postDocument.getId();
        String jsonBodyDataset = createJsonDataset(datasetId,postDocument.getCityName(), postDocument.getDescription(), postDocument.getId()).toString();

        String datasetIDR = "https://ngsi-broker.dev.ecosystem-urbanage.eu/api/dataset";
        ResponseEntity<String> responseEntityDataset = restTemplate.postForEntity(datasetIDR, new HttpEntity<>(jsonBodyDataset, headers), String.class);

    }


    private static JsonObject createJsonDistribution(String id, String title, String description, String downloadURL) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("title", title);
        data.put("description", description);
        data.put("downloadURL", downloadURL);

        return Json.createObjectBuilder()
                .add("person", Json.createObjectBuilder(data))
                .build();
    }


    private static JsonObject createJsonDataset(String id, String title, String description, String distribId) {

        List distribIdOBJ = new ArrayList<String>();
        distribIdOBJ.add(distribId);
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("title", title);
        data.put("description", description);
        data.put("datasetDistribution", distribIdOBJ);

        return Json.createObjectBuilder()
                .add("person", Json.createObjectBuilder(data))
                .build();
    }

}