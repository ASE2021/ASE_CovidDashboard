package com.ase.ase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class BedUtilization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date time;
    private int utilizationIB;
    private int utilizationNB;

    protected BedUtilization() { }

    public BedUtilization(Date time, int utilizationIB, int utilizationNB) {
        this.time = time;
        this.utilizationIB = utilizationIB;
        this.utilizationNB = utilizationNB;
    }

    public BedUtilization(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public float getUtilizationIB() {
        return utilizationIB;
    }

    public void setUtilizationIB(int utilizationIB) {
        this.utilizationIB = utilizationIB;
    }

    public float getUtilizationNB() {
        return utilizationNB;
    }

    public void setUtilizationNB(int utilizationNB) {
        this.utilizationNB = utilizationNB;
    }

    @Override
    public String toString() {
        return "BedUtilization{" +
                "id=" + id +
                ", time=" + time +
                ", utilizationIB=" + utilizationIB +
                ", utilizationNB=" + utilizationNB +
                '}';
    }
}
