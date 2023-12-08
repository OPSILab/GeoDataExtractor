package eu.urbanage.GeoDataExtractor.repository;

import eu.urbanage.GeoDataExtractor.model.Cron;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CronRepository extends MongoRepository<Cron, String> {

    Optional<Cron> findById(String id);


    List<Cron> findByDocumentID(String document_id);


    void deleteById(String id);

}
