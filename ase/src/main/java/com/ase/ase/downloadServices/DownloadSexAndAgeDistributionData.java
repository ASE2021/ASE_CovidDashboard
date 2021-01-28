package com.ase.ase.downloadServices;

import com.ase.ase.dao.SexAndAgeDistributionRepository;
import com.ase.ase.entities.SexAndAgeDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ase.ase.downloadServices.DownloadService.fetchResult;

@Service
public class DownloadSexAndAgeDistributionData {
    @Autowired
    SexAndAgeDistributionRepository sexAndAgeDistributionRepository;

    public void downloadSexAndAgeDistribution() {
        try {
            BufferedReader in = fetchResult("https://covid19-dashboard.ages.at/data/CovidFaelle_Altersgruppe.csv");
            List<SexAndAgeDistribution> list = extractSexAndAgeDistributions(in);
            sexAndAgeDistributionRepository.deleteAll();
            sexAndAgeDistributionRepository.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<SexAndAgeDistribution> extractSexAndAgeDistributions(BufferedReader in) throws IOException {
        List<SexAndAgeDistribution> sexAndAgeDistributionList = new ArrayList<>();
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        while (line != null) {
            String[] cells = line.split(";");
            String ageInterval = "";
            int ageIntervalId = 0;
            String area = "";
            int areaId = 0;
            int population = 0;
            String sex = "";
            int sumCases = 0;
            int sumCured = 0;
            int sumDead = 0;

            for (int i = 0; i<attributes.length; i++) {
                switch (attributes[i]) {
                    case "AltersgruppeID":
                        ageIntervalId = Integer.parseInt(cells[i]);
                        break;
                    case "Altersgruppe":
                        ageInterval = cells[i];
                        break;
                    case "Bundesland":
                        area = cells[i];
                        break;
                    case "BundeslandID":
                        areaId = Integer.parseInt(cells[i]);
                        break;
                    case "AnzEinwohner":
                        population = Integer.parseInt(cells[i]);
                        break;
                    case "Geschlecht":
                        sex = cells[i];
                        break;
                    case "Anzahl":
                        sumCases = Integer.parseInt(cells[i]);
                        break;
                    case "AnzahlGeheilt":
                        sumCured = Integer.parseInt(cells[i]);
                        break;
                    case "AnzahlTot":
                        sumDead = Integer.parseInt(cells[i]);
                        break;
                }
            }

            sexAndAgeDistributionList.add(new SexAndAgeDistribution(ageInterval, ageIntervalId, area, areaId, population, sex, sumCases, sumCured, sumDead));
            line = in.readLine();
        }
        return sexAndAgeDistributionList;
    }
}
