package eu.urbanage.GeoDataExtractor.service;

import eu.urbanage.GeoDataExtractor.DTO.DatasetDTO;
import eu.urbanage.GeoDataExtractor.DTO.DistributionDTO;
import eu.urbanage.GeoDataExtractor.model.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class IDRAService{


    @Value("${HOST_NGSIBROKER}")
    private String hostNGSIBroker;

    @Value("${SERVER_URL}")
    private String serverURL;

    RestTemplate restTemplate = new RestTemplate();


    IDRAService(@Value("${HOST_NGSIBROKER}") String hostNGSIBroker,@Value("${SERVER_URL}") String serverURL) {
        this.hostNGSIBroker = hostNGSIBroker;
        this.serverURL=serverURL;
    }

    public void postOnIDRA(Document postDocument){

        String distributionIDR = hostNGSIBroker+ "/api/distributiondcatap";




        String downloadURL = serverURL + "/api/document/getGeojson/" + postDocument.getId();


        DistributionDTO distributionBody = new DistributionDTO(postDocument.getId(), postDocument.getCityName(), postDocument.getDescription(), downloadURL);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(distributionIDR, distributionBody, String.class);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getStatusCode());

        String datasetId = postDocument.getCityName() + ":" + postDocument.getId();

        DatasetDTO datasetBody = new DatasetDTO(datasetId,postDocument.getCityName(), postDocument.getDescription(), postDocument.getId());


        String datasetIDR = hostNGSIBroker + "/api/dataset";
        ResponseEntity<String> responseEntityDataset = restTemplate.postForEntity(datasetIDR, datasetBody, String.class);

        System.out.println(responseEntityDataset.getStatusCode());

    }


}