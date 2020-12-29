package com.ase.ase.downloadServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DownloadService {
    @Autowired
    DownloadBedAndTestTimelineData downloadBedAndTestTimelineData;
    @Autowired
    DownloadSexAndAgeDistributionData downloadSexAndAgeDistributionData;
    @Autowired
    DownloadCasesTimelineData downloadCasesTimelineData;

    private static Date version;

    public void downloadAllCovidData() {
        if(downloadAndCheckVersion()) {
            downloadBedAndTestTimelineData.downloadTimeline();
            downloadSexAndAgeDistributionData.downloadSexAndAgeDistribution();
            downloadCasesTimelineData.downloadTimeline();
        }
    }

    /**
     * checks the version (date of latest data update) from the data on data.gv and returns
     * if it has changed since the last download.
     * @return true if new version is available (or not able to read the version). If not, return false
     */
    private boolean downloadAndCheckVersion() {
        try {
            BufferedReader in = fetchResult("https://covid19-dashboard.ages.at/data/Version.csv");
            return isNewVersion(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean isNewVersion(BufferedReader in) throws IOException {
        String head = in.readLine();
        String[] attributes = head.split(";");
        String line = in.readLine();
        String[] cells = line.split(";");

        for (int i = 0; i<attributes.length; i++) {
            if(attributes[i].equals("CreationDate")) {
                Date date = null;

                try {
                    date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(cells[i]);
                } catch (ParseException e) {
                    System.out.println("was not able to parse date: " + cells[i]);
                }

                if(version == null || date == null || version.before(date)) {
                    version = new Date();
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    public static void setVersion(Date version) {
        DownloadService.version = version;
    }

    protected static BufferedReader fetchResult(String url) throws IOException {
        URL urlCases = new URL(url);
        HttpURLConnection conCases = (HttpURLConnection) urlCases.openConnection();
        if (conCases.getResponseCode() != 200) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("error (" + e.getMessage() + ") occured trying to download Covid data. RETRY");
            }
            conCases = (HttpURLConnection) urlCases.openConnection();
        }

        return new BufferedReader(new InputStreamReader(conCases.getInputStream()));
    }
}
