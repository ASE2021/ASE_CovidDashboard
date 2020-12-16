package com.ase.ase;

import com.ase.ase.entities.SexDistribution;
import com.ase.ase.entities.Timeline;
import com.ase.ase.services.DownloadSexDistributionData;
import com.ase.ase.services.DownloadTimelineData;
import org.junit.Test;

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

public class ExtractTimelineDataTests {
    @Test
    public void testTimelineExtraction() throws IOException {
        File file = new File(getClass().getResource("/CovidFaelle_Timeline.csv").getFile());
        List<Timeline> expectedList = getExpectedTimelineList();
        List<Timeline> actualList = DownloadTimelineData.extractTimelineData(new BufferedReader(new FileReader(file)));
        assertTimelineList(expectedList, actualList);
    }

    private void assertTimelineList(List<Timeline> expectedList, List<Timeline> actualList) {
        assertEquals(expectedList.size(), actualList.size(), "The actual List does not have the same number of elements than expected");
        for (Timeline element : expectedList) {
            assertTrue(containsElement(actualList, element));
        }
    }

    private boolean containsElement(List<Timeline> list, Timeline expected) {
        for (Timeline element : list) {
            if (element.getAreaId() ==expected.getAreaId() && element.getTime().equals(expected.getTime())) {
                return element.getArea().equals(expected.getArea())
                        && element.getNewCases() == expected.getNewCases()
                        && element.getSumCases() == expected.getSumCases()
                        && element.getWeeklyCases() == expected.getWeeklyCases()
                        && element.getInzidenzCases() == expected.getInzidenzCases()
                        && element.getNewDead() == expected.getNewDead()
                        && element.getSumDead() == expected.getSumDead()
                        && element.getNewCured() == expected.getNewCured()
                        && element.getSumCured() == expected.getSumCured();
            }
        }
        return false;
    }

    private List<Timeline> getExpectedTimelineList() {
        List<Timeline> list = new ArrayList<>();
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(),"Burgenland",1,0,0,0,0,0,0,0,0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(),"Kärnten",2,0,0,0,0,0,0,0,0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(),"Niederösterreich",3,0,0,0,0,0,0,0,0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(),"Oberösterreich",4,0,0,0,0,0,0,0,0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(),"Salzburg",5,0,0,0,0,0,0,0,0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(),"Steiermark",6,0,0,0,0,0,0,0,0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(),"Tirol",7,0,0,0,0,0,0,0,0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(),"Vorarlberg",8,0,0,0,0,0,0,0,0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(),"Wien",9,1,1,1,0,0,0,0,0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(),"Österreich",10,1,1,1,0,0,0,0,0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(),"Burgenland",1,60,8563,402,136,1,144,74,7382));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(),"Kärnten",2,230,18849,1684,300,3,331,165,11023));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(),"Niederösterreich",3,358,48266,2988,177,5,692,368,36082));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(),"Oberösterreich",4,538,66657,3994,268,16,903,280,56011));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(),"Salzburg",5,208,25194,1830,327,0,281,195,21853));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(),"Steiermark",6,540,38353,2963,237,9,871,416,28977));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(),"Tirol",7,134,37634,1329,175,3,459,295,33894));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(),"Vorarlberg",8,131,17670,816,205,0,192,166,15626));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(),"Wien",9,337,67091,2958,154,16,905,9,19152));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(),"Österreich",10,2536,328277,18964,213,53,4778,1968,230000));
        return list;
    }
}
