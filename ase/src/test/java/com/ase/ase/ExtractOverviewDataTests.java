package com.ase.ase;

import com.ase.ase.entities.Overview;
import com.ase.ase.downloadServices.DownloadOverview;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtractOverviewDataTests {
    @Test
    public void testOverviewExtraction() throws IOException {
        File file = new File(getClass().getResource("/AllgemeinDaten.csv").getFile());
        Overview expected = new Overview(3474856, 330343, 329361, 34537, 291042, 4764, 4452);
        Overview actual = DownloadOverview.extractOverviewData(new BufferedReader(new FileReader(file)));
        assertOverview(expected, actual);
    }

    private void assertOverview(Overview expected, Overview actual) {
        assertEquals(expected.getSumTested(), actual.getSumTested());
        assertEquals(expected.getSumCasesConfirmed(), actual.getSumCasesConfirmed());
        assertEquals(expected.getSumTested(), actual.getSumTested());
        assertEquals(expected.getCurrentSick(), actual.getCurrentSick());
        assertEquals(expected.getSumCurred(), actual.getSumCurred());
        assertEquals(expected.getSumDeadReported(), actual.getSumDeadReported());
        assertEquals(expected.getSumDeadConfirmed(), actual.getSumDeadConfirmed());
    }
}
