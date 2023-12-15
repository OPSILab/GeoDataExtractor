package eu.urbanage.GeoDataExtractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.urbanage.GeoDataExtractor.utils.OrionQueryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterService implements FilterClient{

    RestTemplate restTemplate = new RestTemplate();

    private String hostContextBroker;

    private String portContextBroker;

    public FilterService(@Value("${contexBroker.host_orion}") String hostContextBroker, @Value("${contexBroker.port_orion}") String portContextBroker) {
        this.hostContextBroker = hostContextBroker;
        this.portContextBroker = portContextBroker;
    }

    public List<String> getAllCityFilter(String city) throws JsonProcessingException {


        OrionQueryBuilder oqb = new OrionQueryBuilder("https://" + hostContextBroker + ":" + portContextBroker + "/ngsi-ld/v1/types");

        String contexBrokerEndpoint = oqb.get();

        ResponseEntity<String> response;


        response = restTemplate.getForEntity(contexBrokerEndpoint, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode nameNode = mapper.readTree(response.getBody());

        List<String> filterList = new ArrayList<String>();


        for(JsonNode filter_node : nameNode.get("typeList")){

            String filter_ = filter_node.asText();

            OrionQueryBuilder oqbCheck = new OrionQueryBuilder(1);
            String contexBrokerQuery = oqbCheck.addIdPattern(filter_, city).addLocationQuery().get();

            try {
                response = restTemplate.getForEntity(contexBrokerQuery, String.class);

            } catch (HttpClientErrorException.NotFound ex) {
                response = null;
            }

            JsonNode nameNode_n = mapper.readTree(response.getBody());

            int response_size = nameNode_n.size();

            if (response_size>=1){
                filterList.add(filter_);
            }
            // Else condition Temp for Santander Escalator
            else {

                //Temp for Santander Escalator
                OrionQueryBuilder oqbCheckTry = new OrionQueryBuilder(1);
                String contexBrokerQueryTry = oqbCheckTry.addIdPattern(filter_, city).addGeometryQuery().get();

                try {
                    response = restTemplate.getForEntity(contexBrokerQueryTry, String.class);
                } catch (HttpClientErrorException.NotFound exT) {
                    response = null;
                }

                JsonNode nameNode_n_try = mapper.readTree(response.getBody());

                int response_size_try = nameNode_n_try.size();

                if (response_size_try >= 1) {
                    filterList.add(filter_);
                }
            }
        }

        return filterList;
    }




}
