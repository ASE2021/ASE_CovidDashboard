package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SexDistributionPerProvinceDto {
    private final int provinceId;
    private final List<SexDistribution> distribution;

    public SexDistributionPerProvinceDto(int provinceId, List<SexDistribution> distribution) {
        this.provinceId = provinceId;
        this.distribution = distribution;
    }


    @JsonProperty("provinceId")
    public int getProvinceId() {
        return provinceId;
    }
    @JsonProperty("maleCases")
    public List<SexDistribution> getDistribution() {
        return distribution;
    }

}
