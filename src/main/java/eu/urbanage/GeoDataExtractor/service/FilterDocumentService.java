package eu.urbanage.GeoDataExtractor.service;

import eu.urbanage.GeoDataExtractor.model.Document;
import eu.urbanage.GeoDataExtractor.model.FilterDocument;
import eu.urbanage.GeoDataExtractor.repository.ConfigRepository;
import eu.urbanage.GeoDataExtractor.repository.FilterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FilterDocumentService {

    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);


    @Autowired
    protected FilterRepository fRepo;


    public ResponseEntity<List<String>> findFilterByCity(String city) {

        try {

            List<FilterDocument> foundFilters = fRepo.findBycityName(city);

            List<String> foundFilter = foundFilters.get(0).getFilter();

            return ResponseEntity.ok().body(foundFilter);

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

    }
