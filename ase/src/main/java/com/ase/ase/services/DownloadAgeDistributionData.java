package com.ase.ase.services;

import com.ase.ase.dao.*;
import com.ase.ase.entities.AgeDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class DownloadAgeDistributionData {
    @Autowired
    AgeDistributionRepository ageDistributionRepository;

    public void downloadAgeDistributionCases() {
        try {
            URL urlCases = new URL("https://info.gesundheitsministerium.at/data/Altersverteilung.csv");
            HttpURLConnection conCases = (HttpURLConnection) urlCases.openConnection();
            if (isFailing(conCases.getResponseCode())) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("error (" + e.getMessage() + ") occured trying to download age distribution of Covid data. RETRY");
                }
                conCases = (HttpURLConnection) urlCases.openConnection();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conCases.getInputStream()));
            List<AgeDistribution> list = extractAgeDistributions(in);

            URL urlDead = new URL("https://info.gesundheitsministerium.at/data/AltersverteilungTodesfaelle.csv");
            HttpURLConnection conDead = (HttpURLConnection) urlDead.openConnection();
            if (isFailing(conDead.getResponseCode())) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("error (" + e.getMessage() + ") occured trying to download age distribution of Covid data. RETRY");
                }
                conDead = (HttpURLConnection) urlDead.openConnection();
            }

            in = new BufferedReader(new InputStreamReader(conDead.getInputStream()));
            list = addAgeDistributions(in, list);
            ageDistributionRepository.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<AgeDistribution> addAgeDistributions(BufferedReader in, List<AgeDistribution> list) throws IOException {
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        while (line != null) {
            String[] cells = line.split(";");
            String ageInterval = "";
            int sumDead = 0;

            for (int i = 0; i<attributes.length; i++) {
                if(attributes[i].equals("Altersgruppe")) {
                    ageInterval = cells[i];
                } else if(attributes[i].equals("Anzahl")) {
                    sumDead = Integer.parseInt(cells[i]);
                }
            }

            updateAgeDistribution(list, ageInterval, sumDead);
            line = in.readLine();
        }
        return list;
    }

    private static void updateAgeDistribution(List<AgeDistribution> list, String ageInterval, int sumDead) {
        for (AgeDistribution agedistribution : list) {
            if(agedistribution.getAgeInterval().equals(ageInterval)) {
                agedistribution.setSumDead(sumDead);
                return;
            }
        }

        //if no agedistribution is found create a new one
        AgeDistribution ageDistribution = new AgeDistribution(ageInterval);
        ageDistribution.setSumDead(sumDead);
        list.add(ageDistribution);
    }

    public static List<AgeDistribution> extractAgeDistributions(BufferedReader in) throws IOException {
        List<AgeDistribution> ageDistributionList = new ArrayList<>();
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        while (line != null) {
            String[] cells = line.split(";");
            String ageInterval = "";
            int sumCases = 0;

            for (int i = 0; i<attributes.length; i++) {
                if(attributes[i].equals("Altersgruppe")) {
                    ageInterval = cells[i];
                } else if(attributes[i].equals("Anzahl")) {
                    sumCases = Integer.parseInt(cells[i]);
                }
            }

            AgeDistribution ageDistribution = new AgeDistribution(ageInterval);
            ageDistribution.setSumCases(sumCases);
            ageDistributionList.add(ageDistribution);
            line = in.readLine();
        }
        return ageDistributionList;
    }

    private boolean isFailing(int status) {
        return  status==HttpURLConnection.HTTP_BAD_GATEWAY ||
                status==HttpURLConnection.HTTP_INTERNAL_ERROR ||
                status==HttpURLConnection.HTTP_BAD_METHOD ||
                status==HttpURLConnection.HTTP_GATEWAY_TIMEOUT ||
                status==HttpURLConnection.HTTP_UNAVAILABLE;
    }
}
