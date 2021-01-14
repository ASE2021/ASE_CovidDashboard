package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


public class Overview {

    private final int currentsick;
    private final int sumdeaths;
    private final int sumcases;
    private final int sumcured;
    
    public Overview(int currentsick, int sumdeaths, int sumcases, int sumcured) {
        this.sumcured = sumcured;
        this.sumcases = sumcases;
        this.currentsick = currentsick;
        this.sumdeaths = sumdeaths;
    }

    @JsonProperty("cured")
    public int getsumcured() {
        return sumcured;
    }

    @JsonProperty("sumCases")
    public int getsumcases() {
        return sumcases;
    }

    @JsonProperty("activeCases")
    public int getcurrentsick(){
        return currentsick;
    }

    @JsonProperty("deaths")
    public int getsumdeaths() {
        return sumdeaths;
    }
}
