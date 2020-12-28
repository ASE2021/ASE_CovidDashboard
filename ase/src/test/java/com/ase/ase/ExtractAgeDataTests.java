package com.ase.ase;

import com.ase.ase.entities.AgeDistribution;
import com.ase.ase.downloadServices.DownloadAgeDistributionData;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtractAgeDataTests {
    @Test
    public void testAgeCasesExtraction() throws IOException {
        File file = new File(getClass().getResource("/Altersverteilung.csv").getFile());
        List<AgeDistribution> expectedList = getExpectedAgeCasesList();
        List<AgeDistribution> actualList = DownloadAgeDistributionData.extractAgeDistributions(new BufferedReader(new FileReader(file)));
        assertAgeDistributionList(expectedList, actualList);
    }

    @Test
    public void testAgeDeadCasesAddition() throws IOException {
        File file = new File(getClass().getResource("/AltersverteilungTodesfaelle.csv").getFile());
        List<AgeDistribution> expectedList = getExpectedAgeCasesAndDeadList();
        List<AgeDistribution> list = getExpectedAgeCasesList();
        DownloadAgeDistributionData.addAgeDistributions(new BufferedReader(new FileReader(file)), list);
        assertAgeDistributionList(expectedList, list);
    }

    private void assertAgeDistributionList(List<AgeDistribution> expectedList, List<AgeDistribution> actualList) {
        assertEquals(expectedList.size(), actualList.size(), "The actual List does not have the same number of elements than expected");
        for (AgeDistribution element : expectedList) {
            assertTrue(containsElement(actualList, element));
        }
    }

    private boolean containsElement(List<AgeDistribution> list, AgeDistribution expected) {
        for (AgeDistribution element : list) {
            if (element.getAgeInterval().equals(expected.getAgeInterval())) {
                return element.getSumCases() == expected.getSumCases() && element.getSumDead() == expected.getSumDead();
            }
        }
        return false;
    }

    private List<AgeDistribution> getExpectedAgeCasesList() {
        List<AgeDistribution> list = new ArrayList<>();
        list.add(new AgeDistribution("<5", 2715, 0));
        list.add(new AgeDistribution("5-14", 18140, 0));
        list.add(new AgeDistribution("15-24", 47168, 0));
        list.add(new AgeDistribution("25-34", 53982, 0));
        list.add(new AgeDistribution("35-44", 48913, 0));
        list.add(new AgeDistribution("45-54", 59249, 0));
        list.add(new AgeDistribution("55-64", 45173, 0));
        list.add(new AgeDistribution("65-74", 22246, 0));
        list.add(new AgeDistribution("75-84", 19113, 0));
        list.add(new AgeDistribution(">84", 12662, 0));
        return list;
    }

    private List<AgeDistribution> getExpectedAgeCasesAndDeadList() {
        List<AgeDistribution> list = new ArrayList<>();
        list.add(new AgeDistribution("<5", 2715, 0));
        list.add(new AgeDistribution("5-14", 18140, 0));
        list.add(new AgeDistribution("15-24", 47168, 1));
        list.add(new AgeDistribution("25-34", 53982, 5));
        list.add(new AgeDistribution("35-44", 48913, 11));
        list.add(new AgeDistribution("45-54", 59249, 59));
        list.add(new AgeDistribution("55-64", 45173, 196));
        list.add(new AgeDistribution("65-74", 22246, 592));
        list.add(new AgeDistribution("75-84", 19113, 1550));
        list.add(new AgeDistribution(">84", 12662, 2038));
        return list;
    }
}
