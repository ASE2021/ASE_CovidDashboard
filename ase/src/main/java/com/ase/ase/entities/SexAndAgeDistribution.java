package com.ase.ase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SexAndAgeDistribution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String ageInterval;
    private int ageIntervalId;
    private String area;
    private int areaId;
    private int population;
    private String sex;
    private int sumCases;
    private int sumCured;
    private int sumDead;

    protected SexAndAgeDistribution() { }

    public SexAndAgeDistribution(String ageInterval, int ageIntervalId, String area, int areaId, int population, String sex, int sumCases, int sumCured, int sumDead) {
        this.ageInterval = ageInterval;
        this.ageIntervalId = ageIntervalId;
        this.area = area;
        this.areaId = areaId;
        this.population = population;
        this.sex = sex;
        this.sumCases = sumCases;
        this.sumCured = sumCured;
        this.sumDead = sumDead;
    }

    public String getAgeInterval() {
        return ageInterval;
    }

    public void setAgeInterval(String ageInterval) {
        this.ageInterval = ageInterval;
    }

    public int getAgeIntervalId() {
        return ageIntervalId;
    }

    public void setAgeIntervalId(int ageIntervalId) {
        this.ageIntervalId = ageIntervalId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSumCases() {
        return sumCases;
    }

    public void setSumCases(int sumCases) {
        this.sumCases = sumCases;
    }

    public int getSumCured() {
        return sumCured;
    }

    public void setSumCured(int sumCured) {
        this.sumCured = sumCured;
    }

    public int getSumDead() {
        return sumDead;
    }

    public void setSumDead(int sumDead) {
        this.sumDead = sumDead;
    }

    @Override
    public String toString() {
        return "SexAndAgeDistribution{" +
                "id=" + id +
                ", ageInterval='" + ageInterval + '\'' +
                ", ageIntervalId=" + ageIntervalId +
                ", area='" + area + '\'' +
                ", areaId=" + areaId +
                ", population=" + population +
                ", sex='" + sex + '\'' +
                ", sumCases=" + sumCases +
                ", sumCured=" + sumCured +
                ", sumDead=" + sumDead +
                '}';
    }
}
