package com.ase.ase.downloadServices;

import com.ase.ase.dao.OverviewRepository;
import com.ase.ase.entities.Overview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;

import static com.ase.ase.downloadServices.DownloadService.fetchResult;

@Service
public class DownloadOverview {

    @Autowired
    OverviewRepository overviewRepository;

    public void downloadOverview() {
        try {
            BufferedReader in = fetchResult("https://info.gesundheitsministerium.at/data/AllgemeinDaten.csv");
            Overview overview = extractOverviewData(in);
            overviewRepository.save(overview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Overview extractOverviewData(BufferedReader in) throws IOException {
        String head = in.readLine();
        String[] attributes = head.split(";");

        String line = in.readLine();
        String[] cells = line.split(";");
        Overview overview = new Overview();

        for (int i = 0; i < attributes.length; i++) {
            switch (attributes[i]) {
                case "AktuelleErkrankungen":
                    overview.setCurrentSick(Integer.parseInt(cells[i]));
                    break;
                case "PositivGetestet":
                    overview.setSumTestedPositive(Integer.parseInt(cells[i]));
                case "Genesen":
                    overview.setSumCurred(Integer.parseInt(cells[i]));
                    break;
                case "TotBestaetigt":
                    overview.setSumDeadConfirmed(Integer.parseInt(cells[i]));
                    break;
                case "TotGemeldet":
                    overview.setSumDeadReported(Integer.parseInt(cells[i]));
                    break;
                case "GesTestungen":
                    overview.setSumTested(Integer.parseInt(cells[i]));
                    break;
                case "GesBesFaelle":
                    overview.setSumCasesConfirmed(Integer.parseInt(cells[i]));
                    break;
            }
        }

        return overview;
    }
}
