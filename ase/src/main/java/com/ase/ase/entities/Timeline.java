package com.ase.ase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Timeline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date time;
    private String area;
    private int areaId;
    private int newCases;
    private int sumCases;
    private int weeklyCases;
    private int inzidenzCases;

    private int newDead;
    private int sumDead;

    private int newCured;
    private int sumCured;

    protected Timeline() { }

    public Timeline(Date time, String area, int areaId, int newCases, int sumCases, int weeklyCases, int inzidenzCases, int newDead, int sumDead, int newCured, int sumCured) {
        this.time = time;
        this.area = area;
        this.areaId = areaId;
        this.newCases = newCases;
        this.sumCases = sumCases;
        this.weeklyCases = weeklyCases;
        this.inzidenzCases = inzidenzCases;
        this.newDead = newDead;
        this.sumDead = sumDead;
        this.newCured = newCured;
        this.sumCured = sumCured;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getSumCases() {
        return sumCases;
    }

    public void setSumCases(int sumCases) {
        this.sumCases = sumCases;
    }

    public int getWeeklyCases() {
        return weeklyCases;
    }

    public void setWeeklyCases(int weeklyCases) {
        this.weeklyCases = weeklyCases;
    }

    public int getInzidenzCases() {
        return inzidenzCases;
    }

    public void setInzidenzCases(int inzidenzCases) {
        this.inzidenzCases = inzidenzCases;
    }

    public int getNewDead() {
        return newDead;
    }

    public void setNewDead(int newDead) {
        this.newDead = newDead;
    }

    public int getSumDead() {
        return sumDead;
    }

    public void setSumDead(int sumDead) {
        this.sumDead = sumDead;
    }

    public int getNewCured() {
        return newCured;
    }

    public void setNewCured(int newCured) {
        this.newCured = newCured;
    }

    public int getSumCured() {
        return sumCured;
    }

    public void setSumCured(int sumCured) {
        this.sumCured = sumCured;
    }

    @Override
    public String toString() {
        return "Timeline{" +
                "id=" + id +
                ", time=" + time +
                ", area='" + area + '\'' +
                ", areaId=" + areaId +
                ", newCases=" + newCases +
                ", sumCases=" + sumCases +
                ", weeklyCases=" + weeklyCases +
                ", inzidenzCases=" + inzidenzCases +
                ", newDead=" + newDead +
                ", sumDead=" + sumDead +
                ", newCured=" + newCured +
                ", sumCured=" + sumCured +
                '}';
    }
}
