package com.ase.ase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Overview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int sumTested;
    private int sumCasesConfirmed;
    private int sumTestedPositive;
    private int currentSick;
    private int sumCurred;
    private int sumDeadReported;
    private int sumDeadConfirmed;

    public Overview() { }

    public Overview(int sumTested, int sumCasesConfirmed, int sumTestedPositive, int currentSick, int sumCurred, int sumDeadReported, int sumDeadConfirmed) {
        this.sumTested = sumTested;
        this.sumCasesConfirmed = sumCasesConfirmed;
        this.sumTestedPositive = sumTestedPositive;
        this.currentSick = currentSick;
        this.sumCurred = sumCurred;
        this.sumDeadReported = sumDeadReported;
        this.sumDeadConfirmed = sumDeadConfirmed;
    }

    public int getSumTested() {
        return sumTested;
    }

    public void setSumTested(int sumTested) {
        this.sumTested = sumTested;
    }

    public int getSumCasesConfirmed() {
        return sumCasesConfirmed;
    }

    public void setSumCasesConfirmed(int sumCasesConfirmed) {
        this.sumCasesConfirmed = sumCasesConfirmed;
    }

    public int getSumTestedPositive() {
        return sumTestedPositive;
    }

    public void setSumTestedPositive(int sumTestedPositive) {
        this.sumTestedPositive = sumTestedPositive;
    }

    public int getCurrentSick() {
        return currentSick;
    }

    public void setCurrentSick(int currentSick) {
        this.currentSick = currentSick;
    }

    public int getSumCurred() {
        return sumCurred;
    }

    public void setSumCurred(int sumCurred) {
        this.sumCurred = sumCurred;
    }

    public int getSumDeadReported() {
        return sumDeadReported;
    }

    public void setSumDeadReported(int sumDeadReported) {
        this.sumDeadReported = sumDeadReported;
    }

    public int getSumDeadConfirmed() {
        return sumDeadConfirmed;
    }

    public void setSumDeadConfirmed(int sumDeadConfirmed) {
        this.sumDeadConfirmed = sumDeadConfirmed;
    }

    @Override
    public String toString() {
        return "Overview{" +
                "id=" + id +
                ", sumTested=" + sumTested +
                ", sumCasesConfirmed=" + sumCasesConfirmed +
                ", sumTestedPositive=" + sumTestedPositive +
                ", currentSick=" + currentSick +
                ", sumCurred=" + sumCurred +
                ", sumDeadReported=" + sumDeadReported +
                ", sumDeadConfirmed=" + sumDeadConfirmed +
                '}';
    }
}
