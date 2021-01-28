package com.ase.ase.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = @Index(name = "btT_index", columnList = "time, areaId", unique = true))
public class BedAndTestTimeline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date time;
    private String area;
    private int areaId;
    private int sumTested;
    private int freeNB;
    private int freeIB;
    private int usedNB;
    private int usedIB;

    public BedAndTestTimeline() { }

    public BedAndTestTimeline(Date time, String area, int areaId, int sumTested, int freeNB, int freeIB, int usedNB, int usedIB) {
        this.time = time;
        this.area = area;
        this.areaId = areaId;
        this.sumTested = sumTested;
        this.freeNB = freeNB;
        this.freeIB = freeIB;
        this.usedNB = usedNB;
        this.usedIB = usedIB;
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

    public int getSumTested() {
        return sumTested;
    }

    public void setSumTested(int sumTested) {
        this.sumTested = sumTested;
    }

    public int getFreeNB() {
        return freeNB;
    }

    public void setFreeNB(int freeNB) {
        this.freeNB = freeNB;
    }

    public int getFreeIB() {
        return freeIB;
    }

    public void setFreeIB(int freeIB) {
        this.freeIB = freeIB;
    }

    public int getUsedNB() {
        return usedNB;
    }

    public void setUsedNB(int usedNB) {
        this.usedNB = usedNB;
    }

    public int getUsedIB() {
        return usedIB;
    }

    public void setUsedIB(int usedIB) {
        this.usedIB = usedIB;
    }

    @Override
    public String toString() {
        return "Overview{" +
                "id=" + id +
                ", date=" + time +
                ", area='" + area + '\'' +
                ", areaId=" + areaId +
                ", sumTested=" + sumTested +
                ", freeNB=" + freeNB +
                ", freeIB=" + freeIB +
                ", usedNB=" + usedNB +
                ", usedIB=" + usedIB +
                '}';
    }
}
