package com.ase.ase.downloadServices;

import com.ase.ase.dao.PopulationRepository;
import com.ase.ase.entities.Population;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ase.ase.downloadServices.DownloadService.fetchResult;

@Service
public class DownloadPopulationData {
    @Autowired
    PopulationRepository populationRepository;

    public void downloadPopulation() {
        try {
            BufferedReader in = fetchResult("https://covid19-dashboard.ages.at/data/CovidFaelle_Timeline.csv");
            List<Population> list = extractPopulation(in);

            in = fetchResult("https://covid19-dashboard.ages.at/data/CovidFaelle_Timeline_GKZ.csv");
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
}
