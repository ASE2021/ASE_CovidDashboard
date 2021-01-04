package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SexDistribution {
    private final int maleCases;
    private final int femaleCases;
    private final int maleDeaths;
    private final int femaleDeaths;


    public SexDistribution(int maleCases, int femaleCases, int maleDeaths, int femaleDeaths) {
        this.maleCases = maleCases;
        this.femaleCases = femaleCases;
        this.maleDeaths = maleDeaths;
        this.femaleDeaths = femaleDeaths;
    }

    @JsonProperty("maleCases")
    public int getMaleCases() { return maleCases; }

    @JsonProperty("femaleCases")
    public int getFemaleCases() { return femaleCases; }

    @JsonProperty("femaleDeaths")
    public int getFemaleDeaths() { return femaleDeaths; }

    @JsonProperty("maleDeaths")
    public int getMaleDeaths() { return maleDeaths; }
}
