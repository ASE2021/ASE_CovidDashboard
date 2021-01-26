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
    @GetMapping(value = "/new-cases", produces = "application/json")
    @ResponseBody
    public ResponseEntity getRelativeNewCasesBy(@RequestParam("area") Set<Integer> areas, @RequestParam(value = "relative", defaultValue = "false", required = true) boolean relative) {
      String o ="";
        if(relative == true){
            o = casesTimelineRepository.getRelativeNewCasesBy(areas);
        } else {
            o = casesTimelineRepository.getNewCasesBy(areas);
        }
        return ResponseEntity.ok(o);
    }

    @CrossOrigin
    @GetMapping(value = "/deaths", produces = "application/json")
    @ResponseBody
    public ResponseEntity getRelativeNewDeathsBy(@RequestParam("area") Set<Integer> areas, @RequestParam(value = "relative", defaultValue = "false", required = true) boolean relative) {
      String o ="";
        if(relative == true){
            o = casesTimelineRepository.getRelativeNewDeathsBy(areas);
        } else {
            o = casesTimelineRepository.getNewDeathsBy(areas);
        }
        return ResponseEntity.ok(o);
    }

    @CrossOrigin
    @GetMapping(value = "/tests", produces = "application/json")
    @ResponseBody
    public ResponseEntity getRelativeNewTestsBy(@RequestParam("area") Set<Integer> areas, @RequestParam(value = "relative", defaultValue = "false", required = true) boolean relative) {
      String o ="";
        if(relative == true){
            o = casesTimelineRepository.getRelativeNewTestsBy(areas);
        } else {
            o = casesTimelineRepository.getNewTestsBy(areas);
        }
        return ResponseEntity.ok(o);
    }

    @CrossOrigin
    @GetMapping(value = "/cases", produces = "application/json")
    @ResponseBody
    public ResponseEntity getCasesBy(@RequestParam("area") Set<Integer> areas) {
        return ResponseEntity.ok(casesTimelineRepository.getCasesBy(areas));
    }

    @CrossOrigin
    @GetMapping(value = "/generalSituation", produces = "application/json")
    @ResponseBody
    public ResponseEntity getGeneralSituationBy(@RequestParam("area") Set<Integer> areas) {  
        return ResponseEntity.ok(casesTimelineRepository.getGeneralSituationBy(areas));
    }

}
