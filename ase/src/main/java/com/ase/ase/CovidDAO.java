package com.ase.ase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CovidDAO {
    private static Covids list;

    static
    {
        list.getCovidList().add(new Covid(1, "<5", "Burgenland", 1,6271,"M",33,30,0));
        list.getCovidList().add(new Covid(1, "<5", "Burgenland", 1,5908,"W",15,13,0));
        list.getCovidList().add(new Covid(1, "<5", "Kärnten", 2, 122224,"M",46,31,0));
    }

    public Covids getAllCovids()
    {
       return list;
    }
    public Covids getActiveCases()
    {
        return list;
    }
    public Covids getTotalCases()
    {
        return list;
    }
    public Covids getDeaths()
    {
        return list;
    }

    public void addCovid(Covid covid) {
        list.getCovidList().add(covid);
    }
}
