package eu.urbanage.GeoDataExtractor.repository;

import eu.urbanage.GeoDataExtractor.model.Cron;
import eu.urbanage.GeoDataExtractor.model.Document;
import eu.urbanage.GeoDataExtractor.model.FilterDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CronRepository extends MongoRepository<Cron, String> {

    Optional<Cron> findById(String id);


    List<Cron> findByDocumentID(String document_id);


    void deleteById(String id);

}
