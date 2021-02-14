package com.ase.ase;

import com.ase.ase.entities.Population;
import com.ase.ase.downloadServices.DownloadPopulationData;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtractPopulationDataTests {
    @Test
    public void testPopulationProvincesExtraction() throws IOException {
        File file = new File(getClass().getResource("/CovidFaelle_Timeline.csv").getFile());
        List<Population> expectedList = getExpectedPopulationProvincesList();
        List<Population> actualList = DownloadPopulationData.extractPopulation(new BufferedReader(new FileReader(file)));
        assertPopulationList(expectedList, actualList);
    }

    @Test
    public void testPopulationDistrictsExtraction() throws IOException {
        File file = new File(getClass().getResource("/CovidFaelle_Timeline_GKZ.csv").getFile());
        List<Population> expectedList = getExpectedPopulationDistrictsList();
        List<Population> actualList = DownloadPopulationData.extractPopulation(new BufferedReader(new FileReader(file)));
        assertPopulationList(expectedList, actualList);
    }

    private void assertPopulationList(List<Population> expectedList, List<Population> actualList) {
        assertEquals(expectedList.size(), actualList.size(), "The actual List does not have the same number of elements than expected");
        for (Population element : expectedList) {
            assertTrue(containsElement(actualList, element));
        }
    }

    private boolean containsElement(List<Population> list, Population expected) {
        for (Population element : list) {
            if (element.getId() ==expected.getId()) {
                return element.getName().equals(expected.getName()) && element.getPopulation() == expected.getPopulation();
            }
        }
        return false;
    }

    private List<Population> getExpectedPopulationProvincesList() {
        List<Population> list = new ArrayList<>();
        list.add(new Population("Burgenland",1,294436));
        list.add(new Population("Kärnten",2,561293));
        list.add(new Population("Niederösterreich",3,1684287));
        list.add(new Population("Oberösterreich",4,1490279));
        list.add(new Population("Salzburg",5,558410));
        list.add(new Population("Steiermark",6,1246395));
        list.add(new Population("Tirol",7,757634));
        list.add(new Population("Vorarlberg",8,397139));
        list.add(new Population("Wien",9,1911191));
        list.add(new Population("Österreich",10,8901064));
        return list;
    }

    private List<Population> getExpectedPopulationDistrictsList() {
        List<Population> list = new ArrayList<>();
        list.add(new Population("Eisenstadt(Stadt)",101,14816));
        list.add(new Population("Rust(Stadt)",102,1980));
        list.add(new Population("Eisenstadt-Umgebung",103,43236));
        list.add(new Population("Güssing",104,25699));
        list.add(new Population("Jennersdorf",105,17097));
        list.add(new Population("Mattersburg",106,40042));
        list.add(new Population("Neusiedl am See",107,59990));
        list.add(new Population("Oberpullendorf",108,37384));
        list.add(new Population("Oberwart",109,54192));
        list.add(new Population("Klagenfurt Stadt",201,101300));
        list.add(new Population("Villach Stadt",202,62882));
        list.add(new Population("Hermagor",203,18054));
        list.add(new Population("Klagenfurt Land",204,60014));
        list.add(new Population("Sankt Veit an der Glan",205,54186));
        list.add(new Population("Spittal an der Drau",206,75868));
        list.add(new Population("Villach Land",207,64618));
        list.add(new Population("Völkermarkt",208,41862));
        list.add(new Population("Wolfsberg",209,52607));
        list.add(new Population("Feldkirchen",210,29902));
        list.add(new Population("Krems an der Donau(Stadt)",301,25036));
        list.add(new Population("Sankt Pölten(Stadt)",302,55514));
        list.add(new Population("Waidhofen an der Ybbs(Stadt)",303,11222));
        list.add(new Population("Wiener Neustadt(Stadt)",304,45823));
        list.add(new Population("Amstetten",305,116520));
        list.add(new Population("Baden",306,146751));
        list.add(new Population("Bruck an der Leitha",307,103735));
        list.add(new Population("Gänserndorf",308,104782));
        list.add(new Population("Gmünd",309,36553));
        list.add(new Population("Hollabrunn",310,51033));
        list.add(new Population("Horn",311,30936));
        list.add(new Population("Korneuburg",312,91254));
        list.add(new Population("Krems(Land)",313,56487));
        list.add(new Population("Lilienfeld",314,25655));
        list.add(new Population("Melk",315,78191));
        list.add(new Population("Mistelbach",316,75625));
        list.add(new Population("Mödling",317,119115));
        list.add(new Population("Neunkirchen",318,86343));
        list.add(new Population("Sankt Pölten(Land)",319,131729));
        list.add(new Population("Scheibbs",320,41414));
        list.add(new Population("Tulln",321,104593));
        list.add(new Population("Waidhofen an der Thaya",322,25682));
        list.add(new Population("Wiener Neustadt(Land)",323,78307));
        list.add(new Population("Zwettl",325,41987));
        list.add(new Population("Linz(Stadt)",401,206595));
        list.add(new Population("Steyr(Stadt)",402,38056));
        list.add(new Population("Wels(Stadt)",403,62470));
        list.add(new Population("Braunau am Inn",404,105553));
        list.add(new Population("Eferding",405,33178));
        list.add(new Population("Freistadt",406,66861));
        list.add(new Population("Gmunden",407,101859));
        list.add(new Population("Grieskirchen",408,64875));
        list.add(new Population("Kirchdorf an der Krems",409,57071));
        list.add(new Population("Linz-Land",410,151371));
        list.add(new Population("Perg",411,68968));
        list.add(new Population("Ried im Innkreis",412,61690));
        list.add(new Population("Rohrbach",413,56545));
        list.add(new Population("Schärding",414,57391));
        list.add(new Population("Steyr-Land",415,60717));
        list.add(new Population("Urfahr-Umgebung",416,86005));
        list.add(new Population("Vöcklabruck",417,137297));
        list.add(new Population("Wels-Land",418,73777));
        list.add(new Population("Salzburg(Stadt)",501,155021));
        list.add(new Population("Hallein",502,60824));
        list.add(new Population("Salzburg-Umgebung",503,153492));
        list.add(new Population("Sankt Johann im Pongau",504,81194));
        list.add(new Population("Tamsweg",505,20251));
        list.add(new Population("Zell am See",506,87628));
        list.add(new Population("Graz(Stadt)",601,291072));
        list.add(new Population("Deutschlandsberg",603,60867));
        list.add(new Population("Graz-Umgebung",606,156070));
        list.add(new Population("Leibnitz",610,84756));
        list.add(new Population("Leoben",611,59700));
        list.add(new Population("Liezen",612,79652));
        list.add(new Population("Murau",614,27543));
        list.add(new Population("Voitsberg",616,51044));
        list.add(new Population("Weiz",617,90654));
        list.add(new Population("Murtal",620,71698));
        list.add(new Population("Bruck-Mürzzuschlag",621,98697));
        list.add(new Population("Hartberg-Fürstenfeld",622,90606));
        list.add(new Population("Südoststeiermark",623,84036));
        list.add(new Population("Innsbruck-Stadt",701,131961));
        list.add(new Population("Imst",702,60474));
        list.add(new Population("Innsbruck-Land",703,180453));
        list.add(new Population("Kitzbühel",704,64168));
        list.add(new Population("Kufstein",705,110287));
        list.add(new Population("Landeck",706,44386));
        list.add(new Population("Lienz",707,48738));
        list.add(new Population("Reutte",708,32838));
        list.add(new Population("Schwaz",709,84329));
        list.add(new Population("Bludenz",801,64107));
        list.add(new Population("Bregenz",802,134987));
        list.add(new Population("Dornbirn",803,89912));
        list.add(new Population("Feldkirch",804,108133));
        list.add(new Population("Wien",900,1911191));
        return list;
    }
}
