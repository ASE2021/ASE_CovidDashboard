package com.ase.ase.downloadServices;

import com.ase.ase.dao.SexDistributionRepository;
import com.ase.ase.entities.SexDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ase.ase.downloadServices.DownloadService.fetchResult;

@Service
public class DownloadSexDistributionData {
    @Autowired
    SexDistributionRepository sexDistributionRepository;

    public void downloadSexDistributionCases() {
        try {
            BufferedReader in = fetchResult("https://info.gesundheitsministerium.at/data/Geschlechtsverteilung.csv");
            List<SexDistribution> list = extractSexDistributions(in);

            in = fetchResult("https://info.gesundheitsministerium.at/data/VerstorbenGeschlechtsverteilung.csv");
            addSexDistributions(in, list);
            sexDistributionRepository.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addSexDistributions(BufferedReader in, List<SexDistribution> list) throws IOException {
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
}
