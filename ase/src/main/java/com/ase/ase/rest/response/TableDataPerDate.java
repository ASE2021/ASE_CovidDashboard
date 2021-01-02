package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class TableDataPerDate {

    private final String date;
    private final int deaths;
    private final int activeCases;
    private final List<HospitalSituationPerDate> situations;

    public TableDataPerDate(String date, int deaths, int activeCases, List<HospitalSituationPerDate> situations) {
        this.date = date;
        this.deaths = deaths;
        this.activeCases = activeCases;
        this.situations = situations;
    }
    @JsonProperty("date")
    public String getDate() {
        return date;
    }
    @JsonProperty("deaths")
    public int getDeaths() {
        return deaths;
    }
    @JsonProperty("cases")
    public int getCases() {
        return activeCases;
    }
    @JsonProperty("situations")
    public List<HospitalSituationPerDate> getHospitalSituations() {
        return situations;
    }
}
