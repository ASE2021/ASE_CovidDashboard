package com.ase.ase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Service
public class DownloadService {
    @Autowired
    DownloadAgeDistributionData downloadAgeDistributionData;
    @Autowired
    DownloadBedUtilizationData downloadBedUtilizationData;
    @Autowired
    DownloadOverview downloadOverview;
    @Autowired
    DownloadSexDistributionData downloadSexDistributionData;
    @Autowired
    DownloadTimelineData downloadTimelineData;

    private static Date version;

    public void downloadAllCovidData() {
        if(downloadAndCheckVersion()) {
            downloadAgeDistributionData.downloadAgeDistributionCases();
            downloadBedUtilizationData.downloadBedUtilizationCases();
            downloadOverview.downloadOverview();
            downloadSexDistributionData.downloadSexDistributionCases();
            downloadTimelineData.downloadTimeline();
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
