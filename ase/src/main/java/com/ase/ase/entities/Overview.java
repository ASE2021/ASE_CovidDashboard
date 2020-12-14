package com.ase.ase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Overview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int sumTested;
    private int sumCases;
    private int currentSick;
    private int sumDead;

    protected Overview() { }

    public Overview(int numTested, int numPositive, int currentSick, int numDead) {
        this.sumTested = numTested;
        this.sumCases = numPositive;
        this.currentSick = currentSick;
        this.sumDead = numDead;
    }

    public int getSumTested() {
        return sumTested;
    }

    public void setSumTested(int numTested) {
        this.sumTested = numTested;
    }

    public int getSumCases() {
        return sumCases;
    }

    public void setSumCases(int numPositive) {
        this.sumCases = numPositive;
    }

    public int getCurrentSick() {
        return currentSick;
    }

    public void setCurrentSick(int currentSick) {
        this.currentSick = currentSick;
    }

    public int getSumDead() {
        return sumDead;
    }

    public void setSumDead(int numDead) {
        this.sumDead = numDead;
    }

    @Override
    public String toString() {
        return "Overview{" +
                "id=" + id +
                ", sumTested=" + sumTested +
                ", sumCases=" + sumCases +
                ", currentSick=" + currentSick +
                ", sumDead=" + sumDead +
                '}';
    }
}
