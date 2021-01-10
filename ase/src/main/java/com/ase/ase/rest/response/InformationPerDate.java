package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InformationPerDate {
    private final String date;
    private final List<InfoData> values;

    public InformationPerDate(String date, List<InfoData> values) {
        this.date = date;
        this.values = values;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("values")
    public List<InfoData> getValues() {
        return values;
    }

}
