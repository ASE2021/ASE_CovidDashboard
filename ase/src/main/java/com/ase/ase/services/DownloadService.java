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

    public void downloadAllCovidData() {
        downloadAgeDistributionData.downloadAgeDistributionCases();
        downloadBedUtilizationData.downloadBedUtilizationCases();
        downloadOverview.downloadOverview();
        downloadSexDistributionData.downloadSexDistributionCases();
        downloadTimelineData.downloadTimeline();
    }

    protected static BufferedReader fetchResult(String url) throws IOException {
        URL urlCases = new URL(url);
        HttpURLConnection conCases = (HttpURLConnection) urlCases.openConnection();
        if (isFailing(conCases.getResponseCode())) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("error (" + e.getMessage() + ") occured trying to download age distribution of Covid data. RETRY");
            }
            conCases = (HttpURLConnection) urlCases.openConnection();
        }

        return new BufferedReader(new InputStreamReader(conCases.getInputStream()));
    }

    protected static boolean isFailing(int status) {
        return  status== HttpURLConnection.HTTP_BAD_GATEWAY ||
                status==HttpURLConnection.HTTP_INTERNAL_ERROR ||
                status==HttpURLConnection.HTTP_BAD_METHOD ||
                status==HttpURLConnection.HTTP_GATEWAY_TIMEOUT ||
                status==HttpURLConnection.HTTP_UNAVAILABLE;
    }
}
