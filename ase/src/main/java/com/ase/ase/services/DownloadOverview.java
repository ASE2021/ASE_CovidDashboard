package com.ase.ase.services;

import com.ase.ase.dao.OverviewRepository;
import com.ase.ase.entities.Overview;
import com.ase.ase.entities.Timeline;
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
public class DownloadOverview {
    @Autowired
    OverviewRepository overviewRepository;

    public void downloadOverview() {
        try {
            URL urlOverview = new URL("https://covid19-dashboard.ages.at/data/CovidFaelle_Timeline.csv");
            HttpURLConnection conOverview = (HttpURLConnection) urlOverview.openConnection();
            if (isFailing(conOverview.getResponseCode())) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("error (" + e.getMessage() + ") occurred trying to download timeline of Covid. RETRY");
                }
                conOverview = (HttpURLConnection) urlOverview.openConnection();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conOverview.getInputStream()));
            Overview overview = extractOverviewData(in);
            overviewRepository.save(overview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Overview extractOverviewData(BufferedReader in) throws IOException {
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

    private boolean isFailing(int status) {
        return status == HttpURLConnection.HTTP_BAD_GATEWAY ||
                status == HttpURLConnection.HTTP_INTERNAL_ERROR ||
                status == HttpURLConnection.HTTP_BAD_METHOD ||
                status == HttpURLConnection.HTTP_GATEWAY_TIMEOUT ||
                status == HttpURLConnection.HTTP_UNAVAILABLE;
    }
}
