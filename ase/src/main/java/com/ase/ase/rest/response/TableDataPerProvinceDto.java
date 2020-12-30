package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TableDataPerProvinceDto {

    private final int provinceId;
    private final List<TableDataPerDate> tableData;

    public TableDataPerProvinceDto(int provinceId, List<TableDataPerDate> tableData) {
        this.provinceId = provinceId;
        this.tableData = tableData;
    }
    @JsonProperty("provinceId")
    public int getProvinceId() {
        return provinceId;
    }
    @JsonProperty("tableData")
    public List<TableDataPerDate> getTableData() {
        return tableData;
    }
}
