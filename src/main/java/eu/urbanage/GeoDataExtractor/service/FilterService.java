package eu.urbanage.GeoDataExtractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.urbanage.GeoDataExtractor.model.Types;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
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

        String contexBrokerEndpoint = "https://" + hostContextBroker + ":" + portContextBroker + "/ngsi-ld/v1/types";

        ResponseEntity<String> response;

        System.out.println("hostContextBroker: " + hostContextBroker);
        System.out.println("contexBrokerEndpoint: " + contexBrokerEndpoint);

        response = restTemplate.getForEntity(contexBrokerEndpoint, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode nameNode = mapper.readTree(response.getBody());

        List<String> filterList = new ArrayList<String>();

        System.out.println(nameNode.get("typeList"));

        for(JsonNode filter_node : nameNode.get("typeList")){

            String filter_ = filter_node.asText();

            String contexBrokerQuery = "https://" + hostContextBroker + ":" + portContextBroker + "/ngsi-ld/v1/entities?idPattern=urn:ngsi-ld:" + filter_ + ":" + city+":*";

            System.out.println(contexBrokerQuery);
            try {
                response = restTemplate.getForEntity(contexBrokerQuery, String.class);
                // Gestisci la risposta qui, se necessario
            } catch (HttpClientErrorException.NotFound ex) {
                response = null;
                System.out.println("La risorsa richiesta non è stata trovata.");
            }

            JsonNode nameNode_n = mapper.readTree(response.getBody());

            int response_size = nameNode_n.size();

            if (response_size>=1){
                filterList.add(filter_);
            }
        }

        return filterList;
    }




}
