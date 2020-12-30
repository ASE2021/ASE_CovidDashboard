package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DailyDeaths {

    private final String date;
    private final int deaths;

    public DailyDeaths(String date, int deaths) {
        this.date = date;
        this.deaths = deaths;
    }
    @JsonProperty("date")
    public String getDate() {
        return date;
    }
    @JsonProperty("deaths")
    public int getCases() {
        return deaths;
    }
}
