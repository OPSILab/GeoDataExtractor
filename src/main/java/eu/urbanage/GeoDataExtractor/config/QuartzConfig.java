package eu.urbanage.GeoDataExtractor.config;

import eu.urbanage.GeoDataExtractor.Job.FilterJob;
import eu.urbanage.GeoDataExtractor.Job.UserJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QuartzConfig {


    @Bean
    public JobDetail userJobDetail() {
        return JobBuilder.newJob(UserJob.class)
                .withIdentity("userJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger userJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(userJobDetail())
                .withIdentity("userJobTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withMisfireHandlingInstructionNowWithExistingCount() // Esegui subito all'avvio
                        .withIntervalInMinutes(5)
                        .repeatForever()) // Ogni 30 minuti dopo il primo avvio
                .build();
    }

    @Bean
    public JobDetail filterJobDetail() {
        return JobBuilder.newJob(FilterJob.class)
                .withIdentity("filterJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger filterJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(filterJobDetail())
                .withIdentity("filterJobTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withMisfireHandlingInstructionNowWithExistingCount() // Esegui subito all'avvio
                        .withIntervalInMinutes(60)
                        .repeatForever()) // Ogni 60 minuti dopo il primo avvio
                .build();
    }


}
