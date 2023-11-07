package eu.urbanage.GeoDataExtractor.Job;

import eu.urbanage.GeoDataExtractor.controller.CronController;
import eu.urbanage.GeoDataExtractor.model.Cron;
import eu.urbanage.GeoDataExtractor.model.Document;
import eu.urbanage.GeoDataExtractor.model.MultiPolygon;
import eu.urbanage.GeoDataExtractor.service.CronService;
import eu.urbanage.GeoDataExtractor.service.DocumentService;
import eu.urbanage.GeoDataExtractor.service.GeojsonService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class UserJob implements Job {

    @Autowired
    private CronService cs;

    @Autowired
    protected DocumentService ds;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("Eseguo il job dell'utente");

        LocalDateTime startJob = LocalDateTime.now();

        List<Cron> cronList = cs.getAllCron();

        for (Cron selCron : cronList) {
            String document_id = selCron.getDocument_id();

            Document releated_document = ds.findDocumentObj(document_id);

            Date lastExecution = selCron.getData_last_execution();

            int scheduled = selCron.getRepeat();


            LocalDateTime lastExecutionLocal = lastExecution.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            long differenzaInOre = ChronoUnit.HOURS.between(lastExecutionLocal, startJob);

            if (differenzaInOre>=scheduled){

                GeojsonService geoserv = new GeojsonService(new String("orion.ecosystem-urbanage.eu"), new String("443"));

                MultiPolygon testMP = new MultiPolygon();

                testMP.setFilter(releated_document.getFilter());

                testMP.setCityName(releated_document.getCityName());

                testMP.setPolygon(selCron.getMultiPolygon());

                //System.out.println(geoserv.getFromMultiPolygon(testMP));

            }

        }

        }
}
