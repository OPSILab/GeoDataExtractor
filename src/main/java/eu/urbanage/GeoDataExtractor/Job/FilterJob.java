package eu.urbanage.GeoDataExtractor.Job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class FilterJob implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException
    {
        System.out.println("Sto facendo il cron");
    }

}