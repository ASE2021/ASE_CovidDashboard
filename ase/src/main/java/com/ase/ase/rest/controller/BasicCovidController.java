package com.ase.ase.rest.controller;

import com.ase.ase.dao.CasesTimelineRepository;
import com.ase.ase.rest.response.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
                        totalDates
                                .stream()
                                .map(item -> new HospitalSituationPerDate(
                                        item.toString(),
                                        (int) (Math.random() * 100),
                                        (int) (Math.random() * 100))).collect(Collectors.toList())));

    }

    @CrossOrigin
    @GetMapping(value = "/generalsituation/{province-id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<GeneralSituationPerProvinceDto> listNewTableDataFor(@PathVariable("province-id") int provinceId) {
        return ResponseEntity.ok(
                new GeneralSituationPerProvinceDto(provinceId, Arrays.asList(
                        new GeneralSituationPerDate("10.10.2020", 12, 10, 24),
                        new GeneralSituationPerDate("10.10.2020", 12, 12, 24))));


    }

}
