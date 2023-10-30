package eu.urbanage.GeoDataExtractor.Job;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.urbanage.GeoDataExtractor.service.FilterDocumentService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilterJob implements Job {

    @Autowired
    protected FilterDocumentService fds;

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException
    {


        try {
            fds.checkFilter();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Sto aggiornando i filtri");
    }

}