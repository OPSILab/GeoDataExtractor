package eu.urbanage.GeoDataExtractor.repository;

import eu.urbanage.GeoDataExtractor.model.FilterDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilterRepository extends MongoRepository<FilterDocument, String> {

    Optional<FilterDocument> findById(String id);

    void deleteById(String id);

    FilterDocument save(FilterDocument fdoc);

    List<FilterDocument> findBycityName(String city);
}
