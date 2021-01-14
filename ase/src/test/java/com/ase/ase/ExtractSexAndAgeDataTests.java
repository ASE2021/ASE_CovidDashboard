package com.ase.ase;

import com.ase.ase.downloadServices.DownloadSexAndAgeDistributionData;
import com.ase.ase.entities.SexAndAgeDistribution;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtractSexAndAgeDataTests {
    @Test
    public void testSexAndAgeExtraction() throws IOException {
        File file = new File(getClass().getResource("/CovidFaelle_Altersgruppe.csv").getFile());
        List<SexAndAgeDistribution> expectedList = getExpectedSexAndAgeList();
        List<SexAndAgeDistribution> actualList = DownloadSexAndAgeDistributionData.extractSexAndAgeDistributions(new BufferedReader(new FileReader(file)));
        assertSexAndAgeDistributionList(expectedList, actualList);
    }

    private void assertSexAndAgeDistributionList(List<SexAndAgeDistribution> expectedList, List<SexAndAgeDistribution> actualList) {
        assertEquals(expectedList.size(), actualList.size(), "The actual List does not have the same number of elements than expected");
        for (SexAndAgeDistribution element : expectedList) {
            assertTrue(containsElement(actualList, element));
        }
    }

    private boolean containsElement(List<SexAndAgeDistribution> list, SexAndAgeDistribution expected) {
        for (SexAndAgeDistribution element : list) {
            if (element.getAgeIntervalId() == expected.getAgeIntervalId() && element.getAreaId() == expected.getAreaId() && element.getSex().equals(expected.getSex())) {
                return element.getAgeInterval().equals(expected.getAgeInterval())
                        && element.getArea().equals(expected.getArea())
                        && element.getPopulation() == expected.getPopulation()
                        && element.getSumCases() == expected.getSumCases()
                        && element.getSumCured() == expected.getSumCured()
                        && element.getSumDead() == expected.getSumDead();
            }
        }
        return false;
    }

    private List<SexAndAgeDistribution> getExpectedSexAndAgeList() {
        List<SexAndAgeDistribution> list = new ArrayList<>();
        list.add(new SexAndAgeDistribution("<5", 1, "Burgenland",1,6271,"M",37,32,0));
        list.add(new SexAndAgeDistribution("<5", 1, "Burgenland",1,5908,"W",17,13,0));
        list.add(new SexAndAgeDistribution("5-14", 2,"Kärnten",2,26135,"M",363,251,0));
        list.add(new SexAndAgeDistribution("5-14",2, "Kärnten",2,24667,"W",338,255,0));
        list.add(new SexAndAgeDistribution("15-24",3, "Niederösterreich",3,89919,"M",3852,3185,0));
        list.add(new SexAndAgeDistribution("15-24",3, "Niederösterreich",3,83156,"W",3311,2601,0));
        list.add(new SexAndAgeDistribution("25-34",4, "Oberösterreich",4,102058,"M",5639,5049,0));
        list.add(new SexAndAgeDistribution("25-34",4, "Oberösterreich",4,95289,"W",5635,5030,1));
        list.add(new SexAndAgeDistribution("35-44",5, "Salzburg",5,36981,"M",1883,1746,0));
        list.add(new SexAndAgeDistribution("35-44",5, "Salzburg",5,36583,"W",1911,1784,0));
        list.add(new SexAndAgeDistribution("45-54",6, "Steiermark",6,94416,"M",3419,2908,7));
        list.add(new SexAndAgeDistribution("45-54",6, "Steiermark",6,92354,"W",3949,3365,3));
        list.add(new SexAndAgeDistribution("55-64",7, "Tirol",7,51443,"M",2680,2486,10));
        list.add(new SexAndAgeDistribution("55-64",7, "Tirol",7,52659,"W",2590,2439,3));
        list.add(new SexAndAgeDistribution("65-74",8, "Vorarlberg",8,16802,"M",603,521,22));
        list.add(new SexAndAgeDistribution("65-74",8, "Vorarlberg",8,18708,"W",651,587,7));
        list.add(new SexAndAgeDistribution("75-84",9, "Wien",9,48860,"M",1334,240,195));
        list.add(new SexAndAgeDistribution("75-84",9, "Wien",9,69284,"W",1753,303,141));
        list.add(new SexAndAgeDistribution(">84",10, "Österreich",10,74325,"M",3901,1900,1019));
        list.add(new SexAndAgeDistribution(">84",10, "Österreich",10,152000,"W",9360,5033,1422));
        return list;
    }
}
