package com.ase.ase.services;

import com.ase.ase.dao.BedUtilizationRepository;
import com.ase.ase.entities.BedUtilization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DownloadBedUtilizationData {
    @Autowired
    BedUtilizationRepository bedUtilizationRepository;

    public void downloadBedUtilizationCases() {
        try {
            URL urlNB = new URL("https://info.gesundheitsministerium.at/data/NBAuslastung.csv");
            HttpURLConnection conNB = (HttpURLConnection) urlNB.openConnection();
            if (isFailing(conNB.getResponseCode())) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("error (" + e.getMessage() + ") occured trying to download bed utilization of Covid data. RETRY");
                }
                conNB = (HttpURLConnection) urlNB.openConnection();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conNB.getInputStream()));
            List<BedUtilization> list = extractBedUtilizations(in);

            URL urlIB = new URL("https://info.gesundheitsministerium.at/data/IBAuslastung.csv");
            HttpURLConnection conIB = (HttpURLConnection) urlIB.openConnection();
            if (isFailing(conIB.getResponseCode())) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("error (" + e.getMessage() + ") occured trying to download bed utilization of Covid data. RETRY");
                }
                conIB = (HttpURLConnection) urlIB.openConnection();
            }

            in = new BufferedReader(new InputStreamReader(conIB.getInputStream()));
            list = addBedUtilizations(in, list);
            bedUtilizationRepository.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<BedUtilization> addBedUtilizations(BufferedReader in, List<BedUtilization> list) throws IOException {
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
        return list;
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

    private boolean isFailing(int status) {
        return  status==HttpURLConnection.HTTP_BAD_GATEWAY ||
                status==HttpURLConnection.HTTP_INTERNAL_ERROR ||
                status==HttpURLConnection.HTTP_BAD_METHOD ||
                status==HttpURLConnection.HTTP_GATEWAY_TIMEOUT ||
                status==HttpURLConnection.HTTP_UNAVAILABLE;
    }
}
