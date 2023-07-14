package eu.urbanage.GeoDataExtractor.repository;

import eu.urbanage.GeoDataExtractor.model.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface ConfigRepository extends MongoRepository<Polygon, String> {


    /*
    Optional<ShipInfo> findById(String id);

    ShipInfo findByWharfId(String wharfId);

    List<ShipInfo> findAllByWharfId(String wharfId);

    void deleteById(String id);
    */
    @Query("{ 'wharfId' : ?0, 'shipEta' : { $gte: ?1 }, 'shipEtd' : { $lte: ?2 } }")
    List<Polygon> findShipsListBasedOnTime(String wharfId, Date eta, Date etd);

    @Query("{ 'wharfId' : ?0, 'shipEta' : { $gte: ?1 } }")
    List<Polygon> findShipsListBasedOnTime(String wharfId, Date eta);

}
