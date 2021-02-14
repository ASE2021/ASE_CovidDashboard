package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DailyGeneralSituationPerProvinceDto {

    private final int provinceId;
    private final List<GeneralSituationPerDate> tableData;

    public DailyGeneralSituationPerProvinceDto(int provinceId, List<GeneralSituationPerDate> tableData) {
        this.provinceId = provinceId;
        this.tableData = tableData;
    }

    @JsonProperty("provinceId")
    public int getProvinceId() {
        return provinceId;
    }
    @JsonProperty("situations")
    public List<GeneralSituationPerDate> getTableData() {
        return tableData;
    }
}
