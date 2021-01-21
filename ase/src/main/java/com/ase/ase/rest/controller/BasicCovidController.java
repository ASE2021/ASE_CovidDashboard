package com.ase.ase.rest.controller;

import com.ase.ase.dao.CasesTimelineRepository;
import com.ase.ase.dao.BedAndTestTimelineRepository;
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
    private BedAndTestTimelineRepository bedAndTestTimelineRepository;

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
    public static ResponseEntity<DailyHospitalSituationPerProvinceDto> listHospitalCasesFor(@PathVariable("province-id") int provinceId) {
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
                new DailyHospitalSituationPerProvinceDto(
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
    public ResponseEntity<DailyGeneralSituationPerProvinceDto> listNewTableDataFor(@PathVariable("province-id") int provinceId) {
        return ResponseEntity.ok(
                new DailyGeneralSituationPerProvinceDto(provinceId, Arrays.asList(
                        new GeneralSituationPerDate("10.10.2020", 12, 10, 24),
                        new GeneralSituationPerDate("10.10.2020", 12, 12, 24))));


    }
    
    /*
    @CrossOrigin
    @GetMapping(value = "/hospital/", produces = "application/json") 
    @ResponseBody
    public ResponseEntity getHospitalisationsBy(@RequestParam("area") Set<Integer> areas) {
        String g = bedAndTestTimelineRepository.getHospitalisationsBy(areas);
        return ResponseEntity.ok(g);
    }*/
    
       /*
    @CrossOrigin
    @GetMapping(value = "/generalSituation/", produces = "application/json")
    @ResponseBody
    public ResponseEntity getGeneralSituationBy(@RequestParam("area") Set<Integer> areas) {  
        String g = casesTimelineRepository.getGeneralSituationBy(areas);
        return ResponseEntity.ok(g);
    }*/

    @CrossOrigin
    @GetMapping(value = "/new-cases/", produces = "application/json")
    @ResponseBody
    public ResponseEntity getRelativeNewCasesBy(@RequestParam("area") Set<Integer> areas, @RequestParam("relative") boolean relative) {
      String o ="";
        if(relative == true){
            o = casesTimelineRepository.getRelativeNewCasesBy(areas);
        } else {
            o = casesTimelineRepository.getNewCasesBy(areas);
        }
        return ResponseEntity.ok(o);
    }

    @CrossOrigin
    @GetMapping(value = "/deaths/", produces = "application/json")
    @ResponseBody
    public ResponseEntity getRelativeNewDeathsBy(@RequestParam("area") Set<Integer> areas, @RequestParam("relative") boolean relative) {
      String o ="";
        if(relative == true){
            o = casesTimelineRepository.getRelativeNewDeathsBy(areas);
        } else {
            o = casesTimelineRepository.getNewDeathsBy(areas);
        }
        return ResponseEntity.ok(o);
    }

    @CrossOrigin
    @GetMapping(value = "/tests/", produces = "application/json")
    @ResponseBody
    public ResponseEntity getRelativeNewTestsBy(@RequestParam("area") Set<Integer> areas, @RequestParam("relative") boolean relative) {
      String o ="";
        if(relative == true){
            o = casesTimelineRepository.getRelativeNewTestsBy(areas);
        } else {
            o = casesTimelineRepository.getNewTestsBy(areas);
        }
        return ResponseEntity.ok(o);
    }

    @CrossOrigin
    @GetMapping(value = "/cases/", produces = "application/json")
    @ResponseBody
    public ResponseEntity getCasesBy(@RequestParam("area") Set<Integer> areas) {
        String g = casesTimelineRepository.getCasesBy(areas);
        return ResponseEntity.ok(g);
    }

}
