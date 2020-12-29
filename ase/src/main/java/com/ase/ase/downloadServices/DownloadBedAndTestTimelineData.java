package com.ase.ase.downloadServices;

import com.ase.ase.dao.BedAndTestTimelineRepository;
import com.ase.ase.entities.BedAndTestTimeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DownloadBedAndTestTimelineData {
    @Autowired
    BedAndTestTimelineRepository bedAndTestTimelineRepository;

    public void downloadTimeline() {

    }

    public static List<BedAndTestTimeline> extractTimelineData(BufferedReader in) throws IOException {
        List<BedAndTestTimeline> times = new ArrayList<>();
        return times;
    }
}
