package eu.urbanage.GeoDataExtractor.model;

import java.util.List;

public class Types {

    public String id;

    public String type;

    public List<String> typeList;

    public Types(String id, String type, List<String> typeList) {
        this.id = id;
        this.type = type;
        this.typeList = typeList;
    }

}
