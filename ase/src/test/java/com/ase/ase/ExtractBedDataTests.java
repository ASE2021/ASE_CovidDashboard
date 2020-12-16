package com.ase.ase;

import com.ase.ase.entities.BedUtilization;
import com.ase.ase.services.DownloadBedUtilizationData;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtractBedDataTests {
    @Test
    public void testNBedUtilizationExtraction() throws IOException {
        File file = new File(getClass().getResource("/NBAuslastung.csv").getFile());
        List<BedUtilization> expectedList = getExpectedNBedUtilizationList();
        List<BedUtilization> actualList = DownloadBedUtilizationData.extractBedUtilizations(new BufferedReader(new FileReader(file)));
        assertBedUtilizationList(expectedList, actualList);
    }

    @Test
    public void testIBedUtilizationAddition() throws IOException {
        File file = new File(getClass().getResource("/IBAuslastung.csv").getFile());
        List<BedUtilization> expectedList = getExpectedNAndIBedUtilizationList();
        List<BedUtilization> startList = getExpectedNBedUtilizationList();
        List<BedUtilization> actualList = DownloadBedUtilizationData.addBedUtilizations(new BufferedReader(new FileReader(file)), startList);
        assertBedUtilizationList(expectedList, actualList);
    }

    private void assertBedUtilizationList(List<BedUtilization> expectedList, List<BedUtilization> actualList) {
        assertEquals(expectedList.size(), actualList.size(), "The actual List does not have the same number of elements than expected");
        for (BedUtilization element : expectedList) {
            assertTrue(containsElement(actualList, element));
        }
    }

    private boolean containsElement(List<BedUtilization> list, BedUtilization expected) {
        for (BedUtilization element : list) {
            if (element.getTime().equals(expected.getTime())) {
                return element.getUtilizationNB() == expected.getUtilizationNB() && element.getUtilizationIB() == expected.getUtilizationIB();
            }
        }
        return false;
    }

    private List<BedUtilization> getExpectedNBedUtilizationList() {
        List<BedUtilization> list = new ArrayList<>();
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.APRIL, 1).getTime(), 0, 5.498105209069f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.APRIL, 2).getTime(), 0, 5.158580920145f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.APRIL, 3).getTime(), 0, 5.211214483278f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.DECEMBER, 13).getTime(), 0, 38.890396093326f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.DECEMBER, 14).getTime(), 0, 38.497464638377f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), 0, 40.569150398680f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.DECEMBER, 16).getTime(), 0, 40.406607369758f));
        return list;
    }

    private List<BedUtilization> getExpectedNAndIBedUtilizationList() {
        List<BedUtilization> list = new ArrayList<>();
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.APRIL, 1).getTime(), 20.613614573346f, 5.498105209069f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.APRIL, 2).getTime(), 22.670807453416f, 5.158580920145f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.APRIL, 3).getTime(), 26.063829787234f, 5.211214483278f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.DECEMBER, 13).getTime(), 53.327171903881f, 38.890396093326f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.DECEMBER, 14).getTime(), 53.369272237196f, 38.497464638377f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), 53.252788104089f, 40.569150398680f));
        list.add(new BedUtilization(new GregorianCalendar(2020, Calendar.DECEMBER, 16).getTime(), 51.893939393939f, 40.406607369758f));
        return list;
    }
}
