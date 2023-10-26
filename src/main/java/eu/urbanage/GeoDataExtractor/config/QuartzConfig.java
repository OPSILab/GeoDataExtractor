package eu.urbanage.GeoDataExtractor.config;


import eu.urbanage.GeoDataExtractor.Job.TestJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzConfig {

    /**
     ----------------------------
     To complete this config class
     we will add some more code at this location.
     First look at the below lines and understand
     ----------------------------
     **/

    @Bean
    public SimpleTriggerFactoryBean
    createSimpleTriggerFactoryBean(JobDetail jobDetail)
    {
        SimpleTriggerFactoryBean simpleTriggerFactory
                = new SimpleTriggerFactoryBean();

        simpleTriggerFactory.setJobDetail(jobDetail);
        simpleTriggerFactory.setStartDelay(0);
        simpleTriggerFactory.setRepeatInterval(10);
        simpleTriggerFactory.setRepeatCount(10);
        return simpleTriggerFactory;
    }

    @Bean
    public JobDetailFactoryBean createJobDetailFactoryBean(){

        JobDetailFactoryBean jobDetailFactory
                = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(TestJob.class);
        return jobDetailFactory;
    }

    final ApplicationContext applicationContext;

    public QuartzConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    SpringBeanJobFactory createSpringBeanJobFactory (){

        return new SpringBeanJobFactory() {

            @Override
            protected Object createJobInstance
                    (final TriggerFiredBundle bundle) throws Exception {

                final Object job = super.createJobInstance(bundle);

                applicationContext
                        .getAutowireCapableBeanFactory()
                        .autowireBean(job);

                return job;
            }
        };
    }
    @Bean
    public SchedulerFactoryBean createSchedulerFactory
            (SpringBeanJobFactory springBeanJobFactory, Trigger trigger) {

        SchedulerFactoryBean schedulerFactory
                = new SchedulerFactoryBean();
        schedulerFactory.setAutoStartup(true);
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactory.setTriggers(trigger);

        springBeanJobFactory.setApplicationContext(applicationContext);
        schedulerFactory.setJobFactory(springBeanJobFactory);

        return schedulerFactory;
    }
}
