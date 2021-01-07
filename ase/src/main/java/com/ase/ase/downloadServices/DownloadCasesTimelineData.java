package com.ase.ase.downloadServices;

import com.ase.ase.dao.*;
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
public class DownloadCasesTimelineData {
    @Autowired
    CasesTimelineRepository casesTimelineRepository;

    public void downloadTimeline() {
        try {
            BufferedReader in = fetchResult("https://covid19-dashboard.ages.at/data/CovidFaelle_Timeline.csv");
            List<CasesTimeline> list = extractTimelineData(in);

            in = fetchResult("https://covid19-dashboard.ages.at/data/CovidFaelle_Timeline_GKZ.csv");
            list.addAll(extractTimelineData(in));
            casesTimelineRepository.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<CasesTimeline> extractTimelineData(BufferedReader in) throws IOException {
        List<CasesTimeline> times = new ArrayList<>();
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        while (line != null) {
            String[] cells = line.split(";");
            Date time = new Date();
            String area = "";
            int areaId = -1;
            int newCases = 0;
            int sumCases = 0;
            int weeklyCases = 0;
            int inzidenzCases = 0;
            int newDead = 0;
            int sumDead = 0;
            int newCured = 0;
            int sumCured = 0;

            for (int i = 0; i<attributes.length; i++) {
                switch (attributes[i]) {
                    case "Time":
                        try {
                            time = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(cells[i]);
                        } catch (ParseException e) {
                            System.out.println("was not able to parse date: " + cells[i]);
                        }
                        break;
                    case "Bundesland":
                    case "Bezirk":
                        area = cells[i];
                        break;
                    case "BundeslandID":
                    case "GKZ":
                        areaId = Integer.parseInt(cells[i]);
                        break;
                    case "AnzahlFaelle":
                        newCases = Integer.parseInt(cells[i]);
                        break;
                    case "AnzahlFaelleSum":
                        sumCases = Integer.parseInt(cells[i]);
                        break;
                    case "AnzahlFaelle7Tage":
                        weeklyCases = Integer.parseInt(cells[i]);
                        break;
                    case "SiebenTageInzidenzFaelle":
                        inzidenzCases = (int) Float.parseFloat(cells[i].replace(",", "."));
                        break;
                    case "AnzahlTotTaeglich":
                        newDead = Integer.parseInt(cells[i]);
                        break;
                    case "AnzahlTotSum":
                        sumDead = Integer.parseInt(cells[i]);
                        break;
                    case "AnzahlGeheiltTaeglich":
                        newCured = Integer.parseInt(cells[i]);
                        break;
                    case "AnzahlGeheiltSum":
                        sumCured = Integer.parseInt(cells[i]);
                        break;
                }
            }

            times.add(new CasesTimeline(time, area, areaId, newCases, sumCases, weeklyCases, inzidenzCases, newDead, sumDead, newCured, sumCured));
            line = in.readLine();
        }
        return times;
    }
}
