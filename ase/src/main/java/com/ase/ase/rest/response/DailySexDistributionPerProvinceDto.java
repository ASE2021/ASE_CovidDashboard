package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DailySexDistributionPerProvinceDto {
    private final int provinceId;
    private final List<SexDistribution> distribution;

    public DailySexDistributionPerProvinceDto(int provinceId, List<SexDistribution> distribution) {
        this.provinceId = provinceId;
        this.distribution = distribution;
    }


    @JsonProperty("provinceId")
    public int getProvinceId() {
        return provinceId;
    }
    @JsonProperty("cases")
    public List<SexDistribution> getDistribution() {
        return distribution;
    }

}
