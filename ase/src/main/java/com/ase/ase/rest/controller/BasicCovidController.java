package com.ase.ase.rest.controller;

import com.ase.ase.dao.TimelineRepository;
import com.ase.ase.rest.response.DailyCasesPerProvinceDto;

import com.ase.ase.rest.response.HospitalSituationPerDate;
import com.ase.ase.rest.response.HospitalSituationPerDateDto;

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
    @GetMapping(value = "/hospital/{province-id}", produces = "application/json")
    @ResponseBody
    public static ResponseEntity<HospitalSituationPerDateDto> listHospitalCasesFor(@PathVariable("province-id") int provinceId) {
        String s = "2020-02-01";
        String e = "2020-12-31";
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
        }
        return ResponseEntity.ok(
                new HospitalSituationPerDateDto(
                        provinceId,
                        (List<HospitalSituationPerDate>) totalDates
                                .stream()
                                .map(item -> new HospitalSituationPerDate(
                                        item.toString(),
                                        (int) (Math.random() * 100),
                                        (int) (Math.random() * 100)))));
    }


}
