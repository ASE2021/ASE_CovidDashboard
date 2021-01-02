package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TableDataPerDate {

    private final String date;
    private final List<HospitalSituationPerDate> situation;
    private final int cases;
    private final int deaths;

    public TableDataPerDate(String date, int cases, int deaths, List<HospitalSituationPerDate> situation) {
        this.date = date;
        this.situation = situation;
        this.cases = cases;
        this.deaths = deaths;
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
        return cases;
    }

    @JsonProperty("date")
    public List<HospitalSituationPerDate> getHospitalSituation() {
        return situation;
    }
}
