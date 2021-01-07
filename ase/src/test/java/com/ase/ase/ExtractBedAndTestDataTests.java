package com.ase.ase;

import com.ase.ase.downloadServices.DownloadBedAndTestTimelineData;
import com.ase.ase.downloadServices.DownloadCasesTimelineData;
import com.ase.ase.entities.BedAndTestTimeline;
import com.ase.ase.entities.CasesTimeline;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtractBedAndTestDataTests {
    @Test
    public void testTimelineProvincesExtraction() throws IOException {
        File file = new File(getClass().getResource("/CovidFallzahlen.csv").getFile());
        List<BedAndTestTimeline> expectedList = getExpectedTimelineList();
        List<BedAndTestTimeline> actualList = DownloadBedAndTestTimelineData.extractTimelineData(new BufferedReader(new FileReader(file)));
        assertTimelineList(expectedList, actualList);
    }

    private void assertTimelineList(List<BedAndTestTimeline> expectedList, List<BedAndTestTimeline> actualList) {
        assertEquals(expectedList.size(), actualList.size(), "The actual List does not have the same number of elements than expected");
        for (BedAndTestTimeline element : expectedList) {
            assertTrue(containsElement(actualList, element));
        }
    }

    private boolean containsElement(List<BedAndTestTimeline> list, BedAndTestTimeline expected) {
        for (BedAndTestTimeline element : list) {
            if (element.getAreaId() == expected.getAreaId() && element.getTime().equals(expected.getTime())) {
                return element.getArea().equals(expected.getArea())
                        && element.getSumTested() == expected.getSumTested()
                        && element.getFreeNB() == expected.getFreeNB()
                        && element.getFreeIB() == expected.getFreeIB()
                        && element.getUsedNB() == expected.getUsedNB()
                        && element.getUsedIB() == expected.getUsedIB();
            }
        }
        return false;
    }

    private List<BedAndTestTimeline> getExpectedTimelineList() {
        List<BedAndTestTimeline> list = new ArrayList<>();
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 19).getTime(), "Burgenland", 1, 108612, 119, 15, 70, 8));
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 19).getTime(), "Kärnten", 2, 166218, 205, 45, 247, 15));
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 19).getTime(), "Niederösterreich", 3, 639563, 680, 102, 340, 65));
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 19).getTime(), "Oberösterreich", 4, 441828, 670, 57, 530, 100));
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 19).getTime(), "Salzburg", 5, 205286, 125, 15, 143, 30));
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 19).getTime(), "Steiermark", 6, 371778, 541, 76, 436, 78));
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 19).getTime(), "Tirol", 7, 441800, 104, 16, 192, 50));
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 19).getTime(), "Vorarlberg", 8, 205408, 362, 28, 70, 18));
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 19).getTime(), "Wien", 9, 987793, 1888, 185, 447, 125));
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 19).getTime(), "Österreich", 10, 3568286, 4694, 539, 2475, 489));
        list.add(new BedAndTestTimeline(new GregorianCalendar(2020, Calendar.DECEMBER, 20).getTime(), "Burgenland", 1, 108793, 125, 14, 62, 8));
        return list;
    }
}
