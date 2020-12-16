package com.ase.ase.services;

import com.ase.ase.dao.AgeDistributionRepository;
import com.ase.ase.dao.SexDistributionRepository;
import com.ase.ase.entities.AgeDistribution;
import com.ase.ase.entities.SexDistribution;
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
public class DownloadSexDistributionData {
    @Autowired
    SexDistributionRepository sexDistributionRepository;

    public void downloadSexDistributionCases() {
        try {
            URL urlCases = new URL("https://info.gesundheitsministerium.at/data/Geschlechtsverteilung.csv");
            HttpURLConnection conCases = (HttpURLConnection) urlCases.openConnection();
            if (isFailing(conCases.getResponseCode())) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("error (" + e.getMessage() + ") occured trying to download sex distribution of Covid data. RETRY");
                }
                conCases = (HttpURLConnection) urlCases.openConnection();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conCases.getInputStream()));
            List<SexDistribution> list = extractSexDistributions(in);

            URL urlDead = new URL("https://info.gesundheitsministerium.at/data/VerstorbenGeschlechtsverteilung.csv");
            HttpURLConnection conDead = (HttpURLConnection) urlDead.openConnection();
            if (isFailing(conDead.getResponseCode())) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("error (" + e.getMessage() + ") occured trying to download sex distribution of Covid data. RETRY");
                }
                conDead = (HttpURLConnection) urlDead.openConnection();
            }

            in = new BufferedReader(new InputStreamReader(conDead.getInputStream()));
            list = addSexDistributions(in, list);
            sexDistributionRepository.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<SexDistribution> addSexDistributions(BufferedReader in, List<SexDistribution> list) throws IOException {
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        while (line != null) {
            String[] cells = line.split(";");
            String sex = "";
            int deadPercent = 0;

            for (int i = 0; i<attributes.length; i++) {
                if(attributes[i].equals("Geschlecht")) {
                    sex = cells[i];
                } else if(attributes[i].equals("Anzahl in %")) {
                    deadPercent = Integer.parseInt(cells[i]);
                }
            }

            updateSexDistribution(list, sex, deadPercent);
            line = in.readLine();
        }
        return list;
    }

    private static void updateSexDistribution(List<SexDistribution> list, String sex, int deadPercent) {
        for (SexDistribution sexDistribution : list) {
            if(sexDistribution.getSex().equals(sex)) {
                sexDistribution.setDeadPercent(deadPercent);
                return;
            }
        }

        //if no agedistribution is found create a new one
        SexDistribution sexDistribution = new SexDistribution(sex);
        sexDistribution.setDeadPercent(deadPercent);
        list.add(sexDistribution);
    }

    public static List<SexDistribution> extractSexDistributions(BufferedReader in) throws IOException {
        List<SexDistribution> sexDistributionList = new ArrayList<>();
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        while (line != null) {
            String[] cells = line.split(";");
            String sex = "";
            int casesPercent = 0;

            for (int i = 0; i<attributes.length; i++) {
                if(attributes[i].equals("Geschlecht")) {
                    sex = cells[i];
                } else if(attributes[i].equals("Anzahl in %")) {
                    casesPercent = Integer.parseInt(cells[i]);
                }
            }

            SexDistribution sexDistribution = new SexDistribution(sex);
            sexDistribution.setCasesPercent(casesPercent);
            sexDistributionList.add(sexDistribution);
            line = in.readLine();
        }
        return sexDistributionList;
    }

    private boolean isFailing(int status) {
        return  status==HttpURLConnection.HTTP_BAD_GATEWAY ||
                status==HttpURLConnection.HTTP_INTERNAL_ERROR ||
                status==HttpURLConnection.HTTP_BAD_METHOD ||
                status==HttpURLConnection.HTTP_GATEWAY_TIMEOUT ||
                status==HttpURLConnection.HTTP_UNAVAILABLE;
    }
}
