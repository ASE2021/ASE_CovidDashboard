package com.ase.ase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
