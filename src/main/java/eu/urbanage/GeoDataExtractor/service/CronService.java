package eu.urbanage.GeoDataExtractor.service;

import eu.urbanage.GeoDataExtractor.model.Cron;
import eu.urbanage.GeoDataExtractor.repository.CronRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CronService {

    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);



    @Autowired
    protected CronRepository cRepo;




    public Cron addCron(Cron cronjob) {

        try {

            return cRepo.save(cronjob);

        } catch (Exception e) {
            log.error("errore durante il salvataggio {} a causa di: {}", cronjob.getDocument_id(),
                    e.getMessage());
            return null;
        }

    }


    public List<Cron> getAllCron(){

        return cRepo.findAll();

    }

    public ResponseEntity<Cron> updateCron(Cron cron) {

        try {

            Cron foundCron = cRepo.findByDocumentID(cron.getDocument_id()).get(0);

            cRepo.deleteById(foundCron.getId());

            cron.setId(foundCron.getId());
            cron.setCity(foundCron.getCity());
            cron.setFilter(foundCron.getFilter());
            cron.setData_created(foundCron.getData_created());
            cron.setData_last_execution(foundCron.getData_last_execution());

            cRepo.save(cron);

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<Cron> updateCronAfterExec(Cron cron) {

        try {

            Cron foundCron = cRepo.findByDocumentID(cron.getDocument_id()).get(0);

            cRepo.deleteById(foundCron.getId());

            cron.setId(foundCron.getId());
            cron.setCity(foundCron.getCity());
            cron.setFilter(foundCron.getFilter());
            cron.setData_created(foundCron.getData_created());

            cRepo.save(cron);

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<Cron> findCron(String id) {

        try {

            Optional<Cron> foundCron = cRepo.findById(id);


            if (foundCron.isPresent()) {
                return ResponseEntity.ok().body(foundCron.get());
            }
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            log.error("errore durante il reperimento dei dati {} a causa di: {}", id, e.getMessage());
            return null;
        }
    }



    public ResponseEntity<String> deleteCron(String id) {

        try {

            cRepo.deleteById(id);

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
