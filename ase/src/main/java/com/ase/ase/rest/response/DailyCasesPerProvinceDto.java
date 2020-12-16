package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class DailyCasesPerProvinceDto {
    private final int provinceId;
    private final List<CasesPerDate> cases;

    public DailyCasesPerProvinceDto(int provinceId, List<CasesPerDate> cases) {
        this.provinceId = provinceId;
        this.cases = cases;
    }
    @JsonProperty("provinceId")
    public int getProvinceId() {
        return provinceId;
    }
    @JsonProperty("cases")
    public List<CasesPerDate> getCases() {
        return cases;
    }
}
