package com.ase.ase;

import com.ase.ase.downloadServices.DownloadService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class checkDataVersionTests {
    @AfterEach
    private void tearDown() {
        DownloadService.setVersion(null);
    }

    @Test
    public void testCheckSameVersion() throws IOException, ParseException {
        File file = new File(getClass().getResource("/Version.csv").getFile());
        DownloadService.setVersion(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("21.12.2020 14:02:01"));
        boolean isNewVersion = DownloadService.isNewVersion(new BufferedReader(new FileReader(file)));
        assertFalse(isNewVersion);
    }

    @Test
    public void testCheckOlderVersion() throws IOException, ParseException {
        File file = new File(getClass().getResource("/Version.csv").getFile());
        DownloadService.setVersion(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("27.12.2020 14:02:01"));
        boolean isNewVersion = DownloadService.isNewVersion(new BufferedReader(new FileReader(file)));
        assertFalse(isNewVersion);
    }

    @Test
    public void testCheckNewVersion() throws IOException, ParseException {
        File file = new File(getClass().getResource("/Version.csv").getFile());
        DownloadService.setVersion(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("21.12.2020 00:00:00"));
        boolean isNewVersion = DownloadService.isNewVersion(new BufferedReader(new FileReader(file)));
        assertTrue(isNewVersion);
    }

    @Test
    public void testCheckFirstVersion() throws IOException, ParseException {
        File file = new File(getClass().getResource("/Version.csv").getFile());
        DownloadService.setVersion(null);
        boolean isNewVersion = DownloadService.isNewVersion(new BufferedReader(new FileReader(file)));
        assertTrue(isNewVersion);
    }
}
