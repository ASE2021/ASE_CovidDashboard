package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeneralSituationPerDate {

    private final String date;
    private final int hospitalBedsSum;
    private final int cases;
    private final int deaths;

    public GeneralSituationPerDate(String date, int cases, int deaths, int hospitalBedsSum) {
        this.date = date;
        this.hospitalBedsSum = hospitalBedsSum;
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

    @JsonProperty("hospitalBedsSum")
    public int getHospitalSituations(){ return hospitalBedsSum; }

}
