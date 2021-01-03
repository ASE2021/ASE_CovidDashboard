package com.ase.ase.rest.controller;

import com.ase.ase.dao.CasesTimelineRepository;
import com.ase.ase.rest.response.DailyCasesPerProvinceDto;

import com.ase.ase.rest.response.HospitalSituationPerDate;
import com.ase.ase.rest.response.HospitalSituationPerDateDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/daily")
public class BasicCovidController {

    @Autowired
    private CasesTimelineRepository casesTimelineRepository;

    @CrossOrigin
    @GetMapping(value = "/{province-id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<DailyCasesPerProvinceDto> listNewCasesFor(@PathVariable("province-id") int provinceId) {
        return ResponseEntity.ok(
                new DailyCasesPerProvinceDto(
                        provinceId,
                        casesTimelineRepository.findAllBy(provinceId)));
    }

    @CrossOrigin
    @GetMapping(value = "/hospital/{province-id}", produces = "application/json")
    @ResponseBody
    public static ResponseEntity<HospitalSituationPerDateDto> listHospitalCasesFor(@PathVariable("province-id") int provinceId) {
        return ResponseEntity.ok(
                new HospitalSituationPerDateDto(provinceId, Arrays.asList(
                        new HospitalSituationPerDate("10.10.2020", 12, 10),
                        new HospitalSituationPerDate("12.10.2020", 12, 12))));
    }


}
