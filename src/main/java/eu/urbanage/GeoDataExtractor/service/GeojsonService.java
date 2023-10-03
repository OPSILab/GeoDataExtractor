package eu.urbanage.GeoDataExtractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.urbanage.GeoDataExtractor.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeojsonService implements GeojsonClient{


    RestTemplate restTemplate = new RestTemplate();

    private String hostContextBroker;

    private String portContextBroker;

    public GeojsonService(@Value("${contexBroker.host_orion}") String hostContextBroker, @Value("${contexBroker.port_orion}") String portContextBroker) {
        this.hostContextBroker = hostContextBroker;
        this.portContextBroker = portContextBroker;
    }

    @Override
    public List<String> getFromPolygon(Polygon data){
        int limit = 1000;

        //da sistemare
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/geo+json");
        headers.add("Content-Type", "application/json");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        
        

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);


        List<String> GeoData= new ArrayList();

        String contexBrokerEndpoint = "https://" + hostContextBroker + ":" + portContextBroker + "/ngsi-ld/v1/entities";
        ResponseEntity<String> response;
        //da sistemare il limit - offset

        List<String> filter_list = data.getFilter();

        for (String filter: filter_list) {

            String url = contexBrokerEndpoint + "?idPattern=urn:ngsi-ld:" + filter + ":" + data.getCityName() + ":*&georel=intersects&coords="+data.getPolygonString()+"&geometry=Polygon&limit=" + limit;

            response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            List<String> responseArr = Arrays.asList(response.getBody());
            GeoData.addAll(responseArr);
        }

        return GeoData;
    }

    @Override
    public List<String> getFromMultiPolygon(MultiPolygon data){
        int limit = 1000;

        //da sistemare
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/geo+json");
        headers.add("Content-Type", "application/json");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", "*");
        headers.add("Access-Control-Allow-Methods", "*");


        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);


        List<String> GeoData= new ArrayList();

        String contexBrokerEndpoint = "https://" + hostContextBroker + ":" + portContextBroker + "/ngsi-ld/v1/entities";
        ResponseEntity<String> response;
        //da sistemare il limit - offset

        List<String> filter_list = data.getFilter();

        List<List<Coordinates>> multiPolygon = data.getMultiPolygon();

        for (List<Coordinates> polygon : multiPolygon){

            Polygon innerPolygon = new Polygon(polygon, data.getFilter(), data.getCityName());

            for (String filter: filter_list) {

                String url = contexBrokerEndpoint + "?idPattern=urn:ngsi-ld:" + filter + ":" + innerPolygon.getCityName() + ":*&georel=intersects&coords="+innerPolygon.getPolygonString()+"&geometry=Polygon&limit=" + limit;

                response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

                List<String> responseArr = Arrays.asList(response.getBody());
                GeoData.addAll(responseArr);
            }
        }



        return GeoData;
    }
    @Override
    public List<String> getFromPointRadius(PointRadius data) throws JsonProcessingException {
        int limit = 1000;

        String distanceType;


        //da sistemare
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/geo+json");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        if (data.isExternal()){
            distanceType = "minDistance";
        }
        else{
            distanceType = "maxDistance";
        }

        List<String> GeoData= new ArrayList();

        String contexBrokerEndpoint = "https://" + hostContextBroker + ":" + portContextBroker + "/ngsi-ld/v1/entities";
        ResponseEntity<String> response;
        //da sistemare il limit - offset

        List<String> filter_list = data.getFilter();

        for (String filter: filter_list) {

            String url = contexBrokerEndpoint + "?idPattern=urn:ngsi-ld:" + filter + ":" + data.getCityName() + ":*&georel=near;"+ distanceType+":"+data.getRadius()+"&geometry=Point&coords="+data.getPointString()+"&limit=" + limit;

            response = restTemplate.exchange(url
                    , HttpMethod.GET, requestEntity, String.class);
            List<String> responseArr = Arrays.asList(response.getBody());
            GeoData.addAll(responseArr);
        }

        return GeoData;
        //return Arrays.asList(response.getBody());
    }


    @Override
    public List<String> getFromMultiPointRadius(MultiPointRadius data) throws JsonProcessingException {
        int limit = 1000;

        String distanceType;


        //da sistemare
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/geo+json");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);



        List<String> GeoData= new ArrayList();

        String contexBrokerEndpoint = "https://" + hostContextBroker + ":" + portContextBroker + "/ngsi-ld/v1/entities";
        ResponseEntity<String> response;
        //da sistemare il limit - offset

        List<String> filter_list = data.getFilter();
        List<PointRadiusFeature> multiPoint = data.getMultipoint();

        for (PointRadiusFeature pointRadius : multiPoint){

            PointRadius innerPointRadius = new PointRadius(pointRadius.getPoint(), pointRadius.getRadius(), data.getFilter(), data.getCityName(), pointRadius.isExternal());

            if (innerPointRadius.isExternal()){
                distanceType = "minDistance";
            }
            else{
                distanceType = "maxDistance";
            }

            for (String filter: filter_list) {

                String url = contexBrokerEndpoint + "?idPattern=urn:ngsi-ld:" + filter + ":" + innerPointRadius.getCityName() + ":*&georel=near;"+ distanceType+":"+innerPointRadius.getRadius()+"&geometry=Point&coords="+innerPointRadius.getPointString()+"&limit=" + limit;

                response = restTemplate.exchange(url
                        , HttpMethod.GET, requestEntity, String.class);
                List<String> responseArr = Arrays.asList(response.getBody());
                GeoData.addAll(responseArr);
            }

        }

        return GeoData;
        //return Arrays.asList(response.getBody());
    }

}
