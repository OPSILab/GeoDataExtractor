package eu.urbanage.GeoDataExtractor.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.urbanage.GeoDataExtractor.model.GeoJSONFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class GeoJsonUtils {

    public static GeoJSONFeature mergeFeatureCollections(String geojsonString1, String geojsonString2) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode1 = objectMapper.readTree(geojsonString1);
            JsonNode rootNode2 = objectMapper.readTree(geojsonString2);

            if (rootNode1.isArray() && rootNode2.isArray()) {
                List<GeoJSONFeature> allFeatures = new ArrayList<>();

                Iterator<JsonNode> elements1 = rootNode1.elements();
                while (elements1.hasNext()) {
                    JsonNode featureCollection = elements1.next();
                    JsonNode features = featureCollection.get("features");
                    if (features != null && features.isArray()) {
                        for (JsonNode feature : features) {
                            GeoJSONFeature geoJSONFeature = objectMapper.readValue(feature.toString(), GeoJSONFeature.class);
                            allFeatures.add(geoJSONFeature);
                        }
                    }
                }

                Iterator<JsonNode> elements2 = rootNode2.elements();
                while (elements2.hasNext()) {
                    JsonNode featureCollection = elements2.next();
                    JsonNode features = featureCollection.get("features");
                    if (features != null && features.isArray()) {
                        for (JsonNode feature : features) {
                            GeoJSONFeature geoJSONFeature = objectMapper.readValue(feature.toString(), GeoJSONFeature.class);
                            allFeatures.add(geoJSONFeature);
                        }
                    }
                }

                GeoJSONFeature mergedFeature = new GeoJSONFeature();
                mergedFeature.setType("FeatureCollection");
                mergedFeature.getProperties().put("features", allFeatures);
                return mergedFeature;
            } else {
                System.out.println("Entrambi i GeoJSON devono essere di tipo 'FeatureCollection'.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}