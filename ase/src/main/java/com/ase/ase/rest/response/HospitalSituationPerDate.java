package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public final class HospitalSituationPerDate {
    private final String date;
    private final int intenseBeds;
    private final int normalBeds;

    public HospitalSituationPerDate(String date, int intenseBeds, int normalBeds) {
        this.date = date;
        this.intenseBeds = intenseBeds;
        this.normalBeds = normalBeds;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }
    @JsonProperty("intenseBeds")
    public int getIntenseBeds() {
        return intenseBeds;
    }
    @JsonProperty("normalBeds")
    public int getNormalBeds(){ return  normalBeds;}
}
