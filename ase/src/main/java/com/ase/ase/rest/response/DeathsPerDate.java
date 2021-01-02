package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DeathsPerDate {
    private final Date date;
    private final int deaths;

    public DeathsPerDate(Date date, int deaths) {
        this.date = date;
        this.deaths = deaths;
    }
    @JsonProperty("date")
    public Date getDate() {
        return date;
    }
    @JsonProperty("deaths")
    public int getDeaths() {
        return deaths;
    }
}
