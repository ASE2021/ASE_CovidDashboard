package com.ase.ase.downloadServices;

import com.ase.ase.dao.BedAndTestTimelineRepository;
import com.ase.ase.entities.BedAndTestTimeline;
import com.ase.ase.entities.CasesTimeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ase.ase.downloadServices.DownloadService.fetchResult;

@Service
public class DownloadBedAndTestTimelineData {
    @Autowired
    BedAndTestTimelineRepository bedAndTestTimelineRepository;

    public void downloadTimeline() {
        try {
            BufferedReader in = fetchResult("https://covid19-dashboard.ages.at/data/CovidFallzahlen.csv");
            List<BedAndTestTimeline> list = extractTimelineData(in);
            bedAndTestTimelineRepository.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<BedAndTestTimeline> extractTimelineData(BufferedReader in) throws IOException {
        List<BedAndTestTimeline> times = new ArrayList<>();
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        while (line != null) {
            String[] cells = line.split(";");
            Date time = new Date();
            String area = "";
            int areaId = -1;
            int sumTested = 0;
            int freeNB = 0;
            int freeIB = 0;
            int usedNB = 0;
            int usedIB = 0;

            for (int i = 0; i<attributes.length; i++) {
                switch (attributes[i]) {
                    case "MeldeDatum":
                        try {
                            time = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(cells[i]);
                        } catch (ParseException e) {
                            System.out.println("was not able to parse date: " + cells[i]);
                        }
                        break;
                    case "Bundesland":
                        area = cells[i];
                        if(area.equals("Alle")) area = "Österreich";
                        break;
                    case "BundeslandID":
                        areaId = Integer.parseInt(cells[i]);
                        break;
                    case "TestGesamt":
                        sumTested = Integer.parseInt(cells[i]);
                        break;
                    case "FZHospFree":
                        freeNB = Integer.parseInt(cells[i]);
                        break;
                    case "FZICUFree":
                        freeIB = Integer.parseInt(cells[i]);
                        break;
                    case "FZHosp":
                        usedNB = Integer.parseInt(cells[i]);
                        break;
                    case "FZICU":
                        usedIB = (int) Float.parseFloat(cells[i].replace(",", "."));
                        break;
                }
            }

            times.add(new BedAndTestTimeline(time, area, areaId, sumTested, freeNB, freeIB, usedNB, usedIB));
            line = in.readLine();
        }
        return times;
    }
}
