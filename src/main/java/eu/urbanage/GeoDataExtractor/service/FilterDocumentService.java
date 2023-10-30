package eu.urbanage.GeoDataExtractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.urbanage.GeoDataExtractor.model.Document;
import eu.urbanage.GeoDataExtractor.model.FilterDetail;
import eu.urbanage.GeoDataExtractor.model.FilterDocument;
import eu.urbanage.GeoDataExtractor.repository.ConfigRepository;
import eu.urbanage.GeoDataExtractor.repository.FilterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FilterDocumentService {

    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);


    @Autowired
    protected FilterRepository fRepo;


    public ResponseEntity<FilterDetail> findFilterByCity(String city) {

        try {

            List<FilterDocument> foundFilters = fRepo.findBycityName(city);

            List<String> foundFilter = foundFilters.get(0).getFilter();

            FilterDetail responseFilter = new FilterDetail();

            responseFilter.setFilter(foundFilter);

            return ResponseEntity.ok().body(responseFilter);

        } catch (Exception e) {
            log.error("errore durante il reperimento dei dati");
            return null;
        }
    }





    public void updateFilter(List<String> filter_list, String cityName) {

            List<FilterDocument> foundFilter = fRepo.findBycityName(cityName);

            FilterDocument updatedRecord = new FilterDocument();

            updatedRecord.setFilter(filter_list);
            updatedRecord.setCityName(cityName);

            if (!foundFilter.isEmpty()) {
                for (FilterDocument presFilter : foundFilter){
                    String id = presFilter.getId();
                    fRepo.deleteById(id);
                }
            }

            fRepo.save(updatedRecord);
            }

    public void checkFilter() throws JsonProcessingException {

        List<String> city_list = getCityList();

        for (String city : city_list){

            FilterService fs = new FilterService(new String("orion.ecosystem-urbanage.eu"), new String("443"));

            List<String> filter_list = fs.getAllCityFilter(city);

            updateFilter(filter_list, city);

        }

    }
    public ResponseEntity<FilterDetail> retriveFilterList(String city){

        return findFilterByCity(city);

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
