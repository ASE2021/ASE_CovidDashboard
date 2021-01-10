package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


public class Overview {

    private final int sumtested;
    private final int sumcases;
    private final int currentsick;
    private final int sumdeaths;

    public Overview(int sumtested, int sumcases, int currentsick, int sumdeaths) {
        this.sumtested = sumtested;
        this.sumcases = sumcases;
        this.currentsick = currentsick;
        this.sumdeaths = sumdeaths;
    }

    @JsonProperty("sumtested")
    public int getsumtested() {
        return sumtested;
    }

    @JsonProperty("sumcases")
    public int getsumcases() {
        return sumcases;
    }

    @JsonProperty("currentsick")
    public int getcurrentsick(){
        return currentsick;
    }

    @JsonProperty("sumdeaths")
    public int getsumdeaths() {
        return sumdeaths;
    }
}
