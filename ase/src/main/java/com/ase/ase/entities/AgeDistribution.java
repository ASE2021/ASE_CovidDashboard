package com.ase.ase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AgeDistribution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String ageInterval;
    private int sumCases;
    private int sumDead;

    protected AgeDistribution() { }

    public AgeDistribution(String ageInterval, int sumCases, int sumDead) {
        this.ageInterval = ageInterval;
        this.sumCases = sumCases;
        this.sumDead = sumDead;
    }

    public String getAgeInterval() {
        return ageInterval;
    }

    public void setAgeInterval(String ageInterval) {
        this.ageInterval = ageInterval;
    }

    public int getSumCases() {
        return sumCases;
    }

    public void setSumCases(int sumCases) {
        this.sumCases = sumCases;
    }

    public int getSumDead() {
        return sumDead;
    }

    public void setSumDead(int sumDead) {
        this.sumDead = sumDead;
    }

    @Override
    public String toString() {
        return "AgeDistribution{" +
                "id=" + id +
                ", ageInterval='" + ageInterval + '\'' +
                ", sumCases=" + sumCases +
                ", sumDead=" + sumDead +
                '}';
    }
}
