package com.ase.ase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BasicInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int deaths;
    private int cases;
    private int positive;

    public BasicInformation(int deaths, int cases, int positive) {
        this.deaths = deaths;
        this.cases = cases;
        this.positive = positive;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    @Override
    public String toString() {
        return "BasicInformation{" +
                "deaths=" + deaths +
                ", cases=" + cases +
                ", positive=" + positive +
                '}';
    }
}
