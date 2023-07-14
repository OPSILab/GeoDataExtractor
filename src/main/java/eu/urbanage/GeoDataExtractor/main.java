package eu.urbanage.GeoDataExtractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.urbanage.GeoDataExtractor.service.FilterService;

import java.util.List;

public class main {

    public static void main (String[] args) throws JsonProcessingException {

       FilterService fs = new FilterService(new String("localhost"), new String("1026"));


        List<String> test = fs.getAllCityFilter("Helsinki");

        System.out.println(test.toString());
    }
}
