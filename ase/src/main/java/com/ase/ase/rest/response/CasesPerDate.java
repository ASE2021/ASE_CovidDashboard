package com.ase.ase.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public final class CasesPerDate {
    private final Date date;
    private final int cases;

    public CasesPerDate(Date date, int cases) {
        this.date = date;
        this.cases = cases;
    }
    @JsonProperty("date")
    public Date getDate() {
        return date;
    }
    @JsonProperty("cases")
    public int getCases() {
        return cases;
    }
}
