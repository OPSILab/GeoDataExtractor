package eu.urbanage.GeoDataExtractor.config;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class OrionConfig {
    private String orionUrl;

    OrionConfig(@Value("${orion.url}") String orionUrl) {
        this.orionUrl = orionUrl;

    }


    public String getRequest(String endpoint) throws IOException {

        String urlString = this.orionUrl.concat(endpoint);

        URL url = new URL(urlString);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        return "none";

    }


}
