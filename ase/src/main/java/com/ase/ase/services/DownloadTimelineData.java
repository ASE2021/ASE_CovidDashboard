package com.ase.ase.services;

import com.ase.ase.dao.*;
import com.ase.ase.entities.AgeDistribution;
import com.ase.ase.entities.Timeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DownloadTimelineData {
    @Autowired
    TimelineRepository timelineRepository;

    public void downloadTimeline() {
        try {
            URL urlProvinces = new URL("https://covid19-dashboard.ages.at/data/CovidFaelle_Timeline.csv");
            HttpURLConnection conProvinces = (HttpURLConnection) urlProvinces.openConnection();
            if (isFailing(conProvinces.getResponseCode())) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("error (" + e.getMessage() + ") occurred trying to download timeline of Covid. RETRY");
                }
                conProvinces = (HttpURLConnection) urlProvinces.openConnection();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conProvinces.getInputStream()));
            List<Timeline> list = extractTimelineData(in);

            URL urlDistricts = new URL("https://covid19-dashboard.ages.at/data/CovidFaelle_Timeline_GKZ.csv");
            HttpURLConnection conDistricts = (HttpURLConnection) urlDistricts.openConnection();
            if (isFailing(conDistricts.getResponseCode())) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("error (" + e.getMessage() + ") occurred trying to download timeline of Covid. RETRY");
                }
                conDistricts = (HttpURLConnection) urlDistricts.openConnection();
            }

            in = new BufferedReader(new InputStreamReader(conDistricts.getInputStream()));
            list.addAll(extractTimelineData(in));
            timelineRepository.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Timeline> extractTimelineData(BufferedReader in) throws IOException {
        List<Timeline> times = new ArrayList<>();
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

            times.add(new Timeline(time, area, areaId, newCases, sumCases, weeklyCases, inzidenzCases, newDead, sumDead, newCured, sumCured));
            line = in.readLine();
        }
        return times;
    }

    private boolean isFailing(int status) {
        return  status==HttpURLConnection.HTTP_BAD_GATEWAY ||
                status==HttpURLConnection.HTTP_INTERNAL_ERROR ||
                status==HttpURLConnection.HTTP_BAD_METHOD ||
                status==HttpURLConnection.HTTP_GATEWAY_TIMEOUT ||
                status==HttpURLConnection.HTTP_UNAVAILABLE;
    }
}
