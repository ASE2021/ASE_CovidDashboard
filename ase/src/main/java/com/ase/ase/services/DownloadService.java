package com.ase.ase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public boolean downloadAllCovidData() {
        downloadAgeDistributionData.downloadAgeDistributionCases();
        downloadBedUtilizationData.downloadBedUtilizationCases();
        downloadOverview.downloadOverview();
        downloadSexDistributionData.downloadSexDistributionCases();
        downloadTimelineData.downloadTimeline();
        return true;
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
