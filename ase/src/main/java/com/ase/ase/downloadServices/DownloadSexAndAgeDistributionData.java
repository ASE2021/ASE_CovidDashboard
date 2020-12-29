package com.ase.ase.downloadServices;

import com.ase.ase.dao.AgeDistributionRepository;
import com.ase.ase.dao.SexAndAgeDistributionRepository;
import com.ase.ase.entities.AgeDistribution;
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
    }

    public static List<SexAndAgeDistribution> extractSexAndAgeDistributions(BufferedReader in) throws IOException {
        List<SexAndAgeDistribution> sexAndAgeDistributionList = new ArrayList<>();
        return sexAndAgeDistributionList;
    }
}
