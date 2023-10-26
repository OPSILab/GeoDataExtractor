package eu.urbanage.GeoDataExtractor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.urbanage.GeoDataExtractor.model.Filter;
import eu.urbanage.GeoDataExtractor.model.FilterDocument;
import eu.urbanage.GeoDataExtractor.service.DocumentService;
import eu.urbanage.GeoDataExtractor.service.FilterDocumentService;
import eu.urbanage.GeoDataExtractor.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class FilterDocumentController {

    private FilterDocumentService fsd;


    public void checkFilter() throws JsonProcessingException {

        List<String> city_list = getCityList();

        for (String city : city_list){

            FilterService fs = new FilterService(new String("orion.ecosystem-urbanage.eu"), new String("443"));

            List<String> filter_list = fs.getAllCityFilter(city);

            fsd.updateFilter(filter_list, city);

        }

    }

    public ResponseEntity<List<String>> retriveFilterList(String city){

        return fsd.findFilterByCity(city);



    }

    private List<String> getCityList (){
        List<String> stringList = new ArrayList<>();
        stringList.add("Helsinki");
        stringList.add("Flanders");
        stringList.add("Santander");

        System.out.println(stringList);

        return stringList;
    }
}
