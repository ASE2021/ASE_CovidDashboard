package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DailyDeathsPerProvinceDto {

    private final int provinceId;
    private final List<DailyDeaths> deaths;

    public DailyDeathsPerProvinceDto(int provinceId, List<DailyDeaths> deaths) {
        this.provinceId = provinceId;
        this.deaths = deaths;
    }
    @JsonProperty("provinceId")
    public int getProvinceId() {
        return provinceId;
    }
    @JsonProperty("deaths")
    public List<DailyDeaths> getCases() {
        return deaths;
    }
}
