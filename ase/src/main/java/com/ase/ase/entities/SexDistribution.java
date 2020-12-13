package com.ase.ase.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SexDistribution {

    @Id
    private String sex;

    private float CasesPercent;
    private float DeadPercent;
    private float PopulationPercent; //M=49.2% W=50.8%

    protected SexDistribution() { }

    public SexDistribution(String sex, float casesPercent, float deadPercent, float populationPercent) {
        this.sex = sex;
        CasesPercent = casesPercent;
        DeadPercent = deadPercent;
        PopulationPercent = populationPercent;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public float getCasesPercent() {
        return CasesPercent;
    }

    public void setCasesPercent(float casesPercent) {
        CasesPercent = casesPercent;
    }

    public float getPopulationPercent() {
        return PopulationPercent;
    }

    public void setPopulationPercent(float populationPercent) {
        PopulationPercent = populationPercent;
    }

    @Override
    public String toString() {
        return "SexDistribution{" +
                "sex='" + sex + '\'' +
                ", CasesPercent=" + CasesPercent +
                ", DeadPercent=" + DeadPercent +
                ", PopulationPercent=" + PopulationPercent +
                '}';
    }
}