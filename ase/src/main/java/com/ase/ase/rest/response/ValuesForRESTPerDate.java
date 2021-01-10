package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ValuesForRESTPerDate {
    private final String date;
    private final List<ValuesForREST> values;

    public ValuesForRESTPerDate(String date, List<ValuesForREST> values) {
        this.date = date;
        this.values = values;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("values")
    public List<ValuesForREST> getValues() {
        return values;
    }

}
