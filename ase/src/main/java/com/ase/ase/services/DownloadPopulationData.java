package com.ase.ase.services;

import com.ase.ase.dao.PopulationRepository;
import com.ase.ase.entities.Population;
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
public class DownloadPopulationData {
    @Autowired
    PopulationRepository populationRepository;

    public void downloadPopulation() {
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
            List<Population> list = extractPopulation(in);

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
            list.addAll(extractPopulation(in));
            populationRepository.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Population> extractPopulation(BufferedReader in) throws IOException {
        List<Population> populationList = new ArrayList<>();
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        while (line != null) {
            String[] cells = line.split(";");
            int id = -1;
            String name = "";
            int population = 0;

            for (int i = 0; i<attributes.length; i++) {
                switch (attributes[i]) {
                    case "Bundesland":
                    case "Bezirk":
                        name = cells[i];
                        break;
                    case "BundeslandID":
                    case "GKZ":
                        id = Integer.parseInt(cells[i]);
                        break;
                    case "AnzEinwohner":
                        population = Integer.parseInt(cells[i]);
                        break;
                }
            }

            if(id != -1) {
                populationList.add(new Population(name, id, population));
            }
            if(id == 900 || id == 10) break; //population of all districts or provinces is extracted
            line = in.readLine();
        }
        return populationList;
    }

    private boolean isFailing(int status) {
        return  status==HttpURLConnection.HTTP_BAD_GATEWAY ||
                status==HttpURLConnection.HTTP_INTERNAL_ERROR ||
                status==HttpURLConnection.HTTP_BAD_METHOD ||
                status==HttpURLConnection.HTTP_GATEWAY_TIMEOUT ||
                status==HttpURLConnection.HTTP_UNAVAILABLE;
    }
}
