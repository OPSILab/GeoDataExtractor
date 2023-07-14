package eu.urbanage.GeoDataExtractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeoDataExtractorApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoDataExtractorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GeoDataExtractorApplication.class, args);
        LOGGER.info("Start Logging");
    }


}
