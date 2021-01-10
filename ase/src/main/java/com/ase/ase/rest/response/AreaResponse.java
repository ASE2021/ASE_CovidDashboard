package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AreaResponse {
    private final List<Integer> areaId;
    private final String areaName;
    private final List<InformationPerDate> data;

    public AreaResponse(List<Integer> areaId, String areaName, List<InformationPerDate> data) {
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
    public List<InformationPerDate> getCases() {
        return data;
    }
}
