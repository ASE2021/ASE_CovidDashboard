package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ValuesForRESTPerAreaDto {
    private final List<Integer> areaId;
    private final String areaName;
    private final List<ValuesForRESTPerDate> data;

    public ValuesForRESTPerAreaDto(List<Integer> areaId, String areaName, List<ValuesForRESTPerDate> data) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.data = data;
    }

    @JsonProperty("areaId")
    public List<Integer> getAreaId() {
        return areaId;
    }
    @JsonProperty("areaName")
    public String getAreaName() {
        return areaName;
    }
    @JsonProperty("data")
    public List<ValuesForRESTPerDate> getCases() {
        return data;
    }
}
