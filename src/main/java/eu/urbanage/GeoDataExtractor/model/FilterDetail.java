package eu.urbanage.GeoDataExtractor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FilterDetail {


    @JsonProperty("main_filter")
    private List<String> filter;

    @JsonProperty("detail_filter")
    private List<String> filter_detail;


    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }

    public List<String> getFilter_detail() {
        return filter_detail;
    }

    public void setFilter_detail(List<String> filter_detail) {
        this.filter_detail = filter_detail;
    }
}
