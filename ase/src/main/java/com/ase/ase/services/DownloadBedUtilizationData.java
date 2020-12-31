package com.ase.ase.services;

import com.ase.ase.dao.BedUtilizationRepository;
import com.ase.ase.entities.BedUtilization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ase.ase.services.DownloadService.fetchResult;

@Service
public class DownloadBedUtilizationData {
    @Autowired
    BedUtilizationRepository bedUtilizationRepository;

    public void downloadBedUtilizationCases() {
        try {
            BufferedReader in = fetchResult("https://info.gesundheitsministerium.at/data/NBAuslastung.csv");
            List<BedUtilization> list = extractBedUtilizations(in);

            in = fetchResult("https://info.gesundheitsministerium.at/data/IBAuslastung.csv");
            addBedUtilizations(in, list);
            bedUtilizationRepository.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addBedUtilizations(BufferedReader in, List<BedUtilization> list) throws IOException {
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        while (line != null) {
            String[] cells = line.split(";");
            Date time = null;
            float utilizationIB = 0;

            for (int i = 0; i<attributes.length; i++) {
                if(attributes[i].equals("time")) {
                    try {
                        time = new SimpleDateFormat("dd.MM.yyyy").parse(cells[i]);
                    } catch (ParseException e) {
                        System.out.println("was not able to parse date: " + cells[i]);
                    }
                } else if(attributes[i].equals("Belegung Intensivbetten in %")) {
                    utilizationIB = Float.parseFloat(cells[i].replace(",", "."));
                }
            }

            updateBedUtilization(list, time, utilizationIB);
            line = in.readLine();
        }
    }

    private static void updateBedUtilization(List<BedUtilization> list, Date time, float utilizationIB) {
        for (BedUtilization bedUtilization : list) {
            if(bedUtilization.getTime().equals(time)) {
                bedUtilization.setUtilizationIB(utilizationIB);
                return;
            }
        }

        //if no bedUtilization is found create a new one
        BedUtilization bedUtilization = new BedUtilization(time);
        bedUtilization.setUtilizationIB(utilizationIB);
        list.add(bedUtilization);
    }

    public static List<BedUtilization> extractBedUtilizations(BufferedReader in) throws IOException {
        List<BedUtilization> bedUtilizationList = new ArrayList<>();
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        while (line != null) {
            String[] cells = line.split(";");
            Date time = null;
            float utilizationNB = 0;

            for (int i = 0; i<attributes.length; i++) {
                if(attributes[i].equals("time")) {
                    try {
                        time = new SimpleDateFormat("dd.MM.yyyy").parse(cells[i]);
                    } catch (ParseException e) {
                        System.out.println("was not able to parse date: " + cells[i]);
                    }
                } else if(attributes[i].equals("Belegung Normalbetten in %")) {
                    utilizationNB = Float.parseFloat(cells[i].replace(",", "."));
                }
            }

            BedUtilization bedUtilization = new BedUtilization(time);
            bedUtilization.setUtilizationNB(utilizationNB);
            bedUtilizationList.add(bedUtilization);
            line = in.readLine();
        }
        return bedUtilizationList;
    }
}
