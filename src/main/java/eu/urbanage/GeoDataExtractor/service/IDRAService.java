package eu.urbanage.GeoDataExtractor.service;

import eu.urbanage.GeoDataExtractor.DTO.DatasetDTO;
import eu.urbanage.GeoDataExtractor.DTO.DistributionDTO;
import eu.urbanage.GeoDataExtractor.model.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class IDRAService{

    RestTemplate restTemplate = new RestTemplate();

    public void postOnIDRA(Document postDocument){

        String distributionIDR = "https://ngsi-broker.dev.ecosystem-urbanage.eu/api/distributiondcatap";


        String downloadURL = "https://geodata-extractor.dev.ecosystem-urbanage.eu/api/document/getGeojson/" + postDocument.getId();


        DistributionDTO distributionBody = new DistributionDTO(postDocument.getId(), postDocument.getCityName(), postDocument.getDescription(), downloadURL);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(distributionIDR, distributionBody, String.class);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getStatusCode());

        String datasetId = postDocument.getCityName() + ":" + postDocument.getId();

        DatasetDTO datasetBody = new DatasetDTO(datasetId,postDocument.getCityName(), postDocument.getDescription(), postDocument.getId());


        String datasetIDR = "https://ngsi-broker.dev.ecosystem-urbanage.eu/api/dataset";
        ResponseEntity<String> responseEntityDataset = restTemplate.postForEntity(datasetIDR, datasetBody, String.class);

        System.out.println(responseEntityDataset.getStatusCode());

    }


}