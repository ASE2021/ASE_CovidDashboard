package com.ase.ase;

import com.ase.ase.rest.controller.BasicCovidController;
import com.ase.ase.rest.response.HospitalSituationPerDate;
import com.ase.ase.rest.response.HospitalSituationPerDateDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import java.util.Arrays;

public class BasicCovicControllerTest {
    @Test
    public void testListHospitalCases() throws IOException{
        ResponseEntity<HospitalSituationPerDateDto> expectedList = getExpectedSituations();
        ResponseEntity<HospitalSituationPerDateDto> actualList = BasicCovidController.listHospitalCasesFor(10);
        assertHospitalSittuationsList(expectedList, actualList);
    }

    private ResponseEntity<HospitalSituationPerDateDto> getExpectedSituations(){
       ResponseEntity situations = ResponseEntity.ok(new HospitalSituationPerDateDto(10,Arrays.asList(
                new HospitalSituationPerDate[]{new HospitalSituationPerDate("10.10.2020", 12, 10),
                        new HospitalSituationPerDate("12.10.2020", 12, 12)})));
        return situations;
    }

    private void assertHospitalSittuationsList(ResponseEntity<HospitalSituationPerDateDto> expectedList, ResponseEntity<HospitalSituationPerDateDto> actualList){

    }
}
