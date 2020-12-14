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
    private float utilizationIB;
    private float utilizationNB;

    protected BedUtilization() { }

    public BedUtilization(Date time, float utilizationIB, float utilizationNB) {
        this.time = time;
        this.utilizationIB = utilizationIB;
        this.utilizationNB = utilizationNB;
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

    public void setUtilizationIB(float utilizationIB) {
        this.utilizationIB = utilizationIB;
    }

    public float getUtilizationNB() {
        return utilizationNB;
    }

    public void setUtilizationNB(float utilizationNB) {
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
