package eu.urbanage.GeoDataExtractor.service;

import com.fasterxml.jackson.databind.JsonSerializer;
import eu.urbanage.GeoDataExtractor.DTO.DatasetDTO;
import eu.urbanage.GeoDataExtractor.DTO.DistributionDTO;
import eu.urbanage.GeoDataExtractor.GeoDataExtractorApplication;
import eu.urbanage.GeoDataExtractor.model.Document;
import eu.urbanage.GeoDataExtractor.repository.ConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class IDRAService{
    private static final Logger LOGGER = LoggerFactory.getLogger(IDRAService.class);

    @Autowired
    protected ConfigRepository dRepo;

    @Value("${HOST_NGSIBROKER}")
    private String hostNGSIBroker;

    @Value("${SERVER_URL}")
    private String serverURL;

    RestTemplate restTemplate = new RestTemplate();


    IDRAService(@Value("${HOST_NGSIBROKER}") String hostNGSIBroker,@Value("${SERVER_URL}") String serverURL) {
        this.hostNGSIBroker = hostNGSIBroker;
        this.serverURL=serverURL;
    }

    public int postOnIDRA(Document postDocument){

        String distributionIDR = hostNGSIBroker+ "/api/distributiondcatap";




        String downloadURL = serverURL + "/api/document/getGeojson/" + postDocument.getId();


        DistributionDTO distributionBody = new DistributionDTO(postDocument.getId(), postDocument.getCityName(), postDocument.getDescription(), downloadURL);
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(distributionIDR, distributionBody, String.class);
            LOGGER.info(responseEntity.getStatusCode().toString());
        }
        catch (HttpClientErrorException e) {
            HttpStatusCode status = e.getStatusCode();
            LOGGER.warn(status.toString());
        }

        String datasetId = postDocument.getCityName() + ":" + postDocument.getId();

        DatasetDTO datasetBody = new DatasetDTO(datasetId,postDocument.getCityName(), postDocument.getDescription(), postDocument.getId());


        String datasetIDR = hostNGSIBroker + "/api/dataset";

        try {
        ResponseEntity<String> responseEntityDataset = restTemplate.postForEntity(datasetIDR, datasetBody, String.class);
        HttpStatusCode statusDataset = responseEntityDataset.getStatusCode();
        LOGGER.info(statusDataset.toString());
        return statusDataset.value();
        }
        catch (HttpClientErrorException e) {
            HttpStatusCode status = e.getStatusCode();
            LOGGER.warn(status.toString());
            return status.value();

        }


    }


}