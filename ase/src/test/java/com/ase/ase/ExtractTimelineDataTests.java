package com.ase.ase;

import com.ase.ase.entities.Timeline;
import com.ase.ase.downloadServices.DownloadTimelineData;
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

public class ExtractTimelineDataTests {
    @Test
    public void testTimelineProvincesExtraction() throws IOException {
        File file = new File(getClass().getResource("/CovidFaelle_Timeline.csv").getFile());
        List<Timeline> expectedList = getExpectedTimelineProvincesList();
        List<Timeline> actualList = DownloadTimelineData.extractTimelineData(new BufferedReader(new FileReader(file)));
        assertTimelineList(expectedList, actualList);
    }

    @Test
    public void testTimelineDistrictsExtraction() throws IOException {
        File file = new File(getClass().getResource("/CovidFaelle_Timeline_GKZ.csv").getFile());
        List<Timeline> expectedList = getExpectedTimelineDistrictsList();
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
            if (element.getAreaId() == expected.getAreaId() && element.getTime().equals(expected.getTime())) {
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

    private List<Timeline> getExpectedTimelineProvincesList() {
        List<Timeline> list = new ArrayList<>();
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Burgenland", 1, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Kärnten", 2, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Niederösterreich", 3, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Oberösterreich", 4, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Salzburg", 5, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Steiermark", 6, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Tirol", 7, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Vorarlberg", 8, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Wien", 9, 1, 1, 1, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Österreich", 10, 1, 1, 1, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Burgenland", 1, 60, 8563, 402, 136, 1, 144, 74, 7382));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Kärnten", 2, 230, 18849, 1684, 300, 3, 331, 165, 11023));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Niederösterreich", 3, 358, 48266, 2988, 177, 5, 692, 368, 36082));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Oberösterreich", 4, 538, 66657, 3994, 268, 16, 903, 280, 56011));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Salzburg", 5, 208, 25194, 1830, 327, 0, 281, 195, 21853));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Steiermark", 6, 540, 38353, 2963, 237, 9, 871, 416, 28977));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Tirol", 7, 134, 37634, 1329, 175, 3, 459, 295, 33894));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Vorarlberg", 8, 131, 17670, 816, 205, 0, 192, 166, 15626));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Wien", 9, 337, 67091, 2958, 154, 16, 905, 9, 19152));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Österreich", 10, 2536, 328277, 18964, 213, 53, 4778, 1968, 230000));
        return list;
    }

    private List<Timeline> getExpectedTimelineDistrictsList() {
        List<Timeline> list = new ArrayList<>();
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Eisenstadt(Stadt)", 101, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Rust(Stadt)", 102, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Eisenstadt-Umgebung", 103, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Güssing", 104, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Jennersdorf", 105, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Mattersburg", 106, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Neusiedl am See", 107, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Oberpullendorf", 108, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Oberwart", 109, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Klagenfurt Stadt", 201, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Villach Stadt", 202, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Hermagor", 203, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Klagenfurt Land", 204, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Sankt Veit an der Glan", 205, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Spittal an der Drau", 206, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Villach Land", 207, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Völkermarkt", 208, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Wolfsberg", 209, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Feldkirchen", 210, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Krems an der Donau(Stadt)", 301, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Sankt Pölten(Stadt)", 302, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Waidhofen an der Ybbs(Stadt)", 303, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Wiener Neustadt(Stadt)", 304, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Amstetten", 305, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Baden", 306, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Bruck an der Leitha", 307, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Gänserndorf", 308, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Gmünd", 309, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Hollabrunn", 310, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Horn", 311, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Korneuburg", 312, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Krems(Land)", 313, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Lilienfeld", 314, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Melk", 315, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Mistelbach", 316, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Mödling", 317, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Neunkirchen", 318, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Sankt Pölten(Land)", 319, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Scheibbs", 320, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Tulln", 321, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Waidhofen an der Thaya", 322, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Wiener Neustadt(Land)", 323, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Zwettl", 325, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Linz(Stadt)", 401, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Steyr(Stadt)", 402, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Wels(Stadt)", 403, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Braunau am Inn", 404, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Eferding", 405, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Freistadt", 406, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Gmunden", 407, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Grieskirchen", 408, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Kirchdorf an der Krems", 409, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Linz-Land", 410, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Perg", 411, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Ried im Innkreis", 412, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Rohrbach", 413, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Schärding", 414, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Steyr-Land", 415, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Urfahr-Umgebung", 416, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Vöcklabruck", 417, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Wels-Land", 418, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Salzburg(Stadt)", 501, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Hallein", 502, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Salzburg-Umgebung", 503, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Sankt Johann im Pongau", 504, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Tamsweg", 505, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Zell am See", 506, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Graz(Stadt)", 601, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Deutschlandsberg", 603, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Graz-Umgebung", 606, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Leibnitz", 610, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Leoben", 611, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Liezen", 612, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Murau", 614, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Voitsberg", 616, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Weiz", 617, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Murtal", 620, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Bruck-Mürzzuschlag", 621, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Hartberg-Fürstenfeld", 622, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Südoststeiermark", 623, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Innsbruck-Stadt", 701, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Imst", 702, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Innsbruck-Land", 703, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Kitzbühel", 704, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Kufstein", 705, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Landeck", 706, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Lienz", 707, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Reutte", 708, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Schwaz", 709, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Bludenz", 801, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Bregenz", 802, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Dornbirn", 803, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Feldkirch", 804, 0, 0, 0, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.FEBRUARY, 26).getTime(), "Wien", 900, 1, 1, 1, 0, 0, 0, 0, 0));
        list.add(new Timeline(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime(), "Eisenstadt(Stadt)", 101, 2, 432, 11, 74, 0, 6, 4, 353));
        return list;
    }
}
