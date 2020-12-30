package com.ase.ase.rest.controller;

import com.ase.ase.dao.TimelineRepository;
import com.ase.ase.rest.response.CasesPerDate;
import com.ase.ase.rest.response.DailyCasesPerProvinceDto;
import com.ase.ase.rest.response.DailyDeaths;
import com.ase.ase.rest.response.DailyDeathsPerProvinceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/daily")
public class BasicCovidController {

    @Autowired
    private TimelineRepository timelineRepository;

    @CrossOrigin
    @GetMapping(value = "/{province-id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<DailyCasesPerProvinceDto> listNewCasesFor(@PathVariable("province-id") int provinceId) {
        return ResponseEntity.ok(
                new DailyCasesPerProvinceDto(
                        provinceId,
                        timelineRepository.findAllBy(provinceId)));
    }

    @CrossOrigin
    @GetMapping(value = "/deaths/{province-id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<DailyDeathsPerProvinceDto> listNewDeathsFor(@PathVariable("province-id") int provinceId) {
        return ResponseEntity.ok(
                new DailyDeathsPerProvinceDto(provinceId,Arrays.asList(
                        new DailyDeaths("10.10.2020", 120),
                        new DailyDeaths("12.10.2020", 130))));
    }

}
