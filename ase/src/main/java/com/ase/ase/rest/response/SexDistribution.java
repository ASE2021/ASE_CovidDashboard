package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SexDistribution {
    private final int maleCases;
    private final int femaleCases;


    public SexDistribution(int maleCases, int femaleCases) {
        this.maleCases = maleCases;
        this.femaleCases = femaleCases;
    }

    @JsonProperty("maleCases")
    public int getMaleCases() { return maleCases; }

    @JsonProperty("femaleCases")
    public int getFemaleCases() { return femaleCases; }
}
