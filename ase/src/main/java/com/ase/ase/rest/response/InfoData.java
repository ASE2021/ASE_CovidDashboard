package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoData {
    private final String identifier;
    private final int value;

    public InfoData(String identifier, int value) {
        this.identifier = identifier;
        this.value = value;
    }

    @JsonProperty("identifier")
    public String getIdentifier() {
        return identifier;
    }

    @JsonProperty("value")
    public int getValue() {
        return value;
    }
}
