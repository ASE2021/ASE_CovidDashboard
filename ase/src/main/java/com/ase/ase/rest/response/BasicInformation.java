package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


public class BasicInformation {

    private final int sumtested;
    private final int sumcases;
    private final int sumdeaths;

    public BasicInformation(int sumtested, int sumcases, int sumdeaths) {
        this.sumtested = sumtested;
        this.sumcases = sumcases;
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

    @JsonProperty("sumdeaths")
    public int getsumdeaths() {
        return sumdeaths;
    }
}
