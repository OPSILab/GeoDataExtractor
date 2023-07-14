package eu.urbanage.GeoDataExtractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.urbanage.GeoDataExtractor.model.Types;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterService implements FilterClient{


    RestTemplate restTemplate = new RestTemplate();

    private String hostContextBroker;

    private String portContextBroker;

    public FilterService(@Value("${contexBroker.host_orion}") String hostContextBroker, @Value("${contexBroker.port_orion}") String portContextBroker) {
        this.hostContextBroker = hostContextBroker;
        this.portContextBroker = portContextBroker;
    }

    public List<String> getAllCityFilter(String city) throws JsonProcessingException {

        List<String> filterType= new ArrayList<String>();

        String contexBrokerEndpoint = "http://" + hostContextBroker + ":" + portContextBroker + "/ngsi-ld/v1/types";


        ResponseEntity<String> response;

        System.out.println("hostContextBroker: " + hostContextBroker);
        System.out.println("contexBrokerEndpoint: " + contexBrokerEndpoint);

        response = restTemplate.getForEntity(contexBrokerEndpoint, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode nameNode = mapper.readTree(response.getBody());

        List<String> filterList = new ArrayList<String>();

        for(JsonNode filter_node : nameNode.get("typeList")){

            String filter_ = filter_node.asText();

            String contexBrokerQuery = "http://" + hostContextBroker + ":" + portContextBroker + "/ngsi-ld/v1/entities?idPattern=urn:ngsi-ld:" + filter_ + ":" + city+":*";

            System.out.println(contexBrokerQuery);

            response = restTemplate.getForEntity(contexBrokerQuery, String.class);


            List<String> responsefilt = Arrays.asList(response.getBody());

            int response_size = responsefilt.size();

            System.out.println(responsefilt);
            System.out.println(response.getBody());

            if (response_size>=1){
                filterList.add(filter_);
            }
        }

        return filterList;
    }




}
