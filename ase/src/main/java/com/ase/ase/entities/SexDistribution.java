package com.ase.ase.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SexDistribution {

    @Id
    private String sex;

    private int casesPercent;
    private int deadPercent;
    private int populationPercent; //M=49.2% W=50.8%

    protected SexDistribution() { }

    public SexDistribution(String sex, int casesPercent, int deadPercent) {
        this.sex = sex;
        this.casesPercent = casesPercent;
        this.deadPercent = deadPercent;
        if(sex.equals("weiblich")) {
            this.populationPercent = 51;
        } else if(sex.equals("männlich")) {
            this.populationPercent = 49;
        }
    }

    public SexDistribution(String sex) {
        this.sex = sex;
        if(sex.equals("weiblich")) {
            this.populationPercent = 51;
        } else if(sex.equals("männlich")) {
            this.populationPercent = 49;
        }
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public float getCasesPercent() {
        return casesPercent;
    }

    public void setCasesPercent(int casesPercent) {
        this.casesPercent = casesPercent;
    }

    public int getDeadPercent() {
        return deadPercent;
    }

    public void setDeadPercent(int deadPercent) {
        this.deadPercent = deadPercent;
    }

    public float getPopulationPercent() {
        return populationPercent;
    }

    public void setPopulationPercent(int populationPercent) {
        this.populationPercent = populationPercent;
    }

    @Override
    public String toString() {
        return "SexDistribution{" +
                "sex='" + sex + '\'' +
                ", CasesPercent=" + casesPercent +
                ", DeadPercent=" + deadPercent +
                ", PopulationPercent=" + populationPercent +
                '}';
    }
}