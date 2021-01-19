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
    @GetMapping(value = "/hospital/{area}", produces = "application/json") 
    @ResponseBody
    public ResponseEntity getHospitalisationsBy(@RequestParam("area") Set<Integer> areas) {
        String g = bedAndTestTimelineRepository.getHospitalisationsBy(areas);
        return ResponseEntity.ok(g);
    }

    @CrossOrigin
    @GetMapping(value = "/new-cases?{area}&{relative}", produces = "application/json")
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
    @GetMapping(value = "/deaths?{area}&{relative}", produces = "application/json")
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
    @GetMapping(value = "/tests?{area}&{relative}", produces = "application/json")
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
    @GetMapping(value = "/cases?{area}", produces = "application/json")
    @ResponseBody
    public ResponseEntity getCasesBy(@RequestParam("area") Set<Integer> areas) {
        String g = casesTimelineRepository.getCasesBy(areas);
        return ResponseEntity.ok(g);
    }

    @CrossOrigin
    @GetMapping(value = "/generalSituation?{area}", produces = "application/json")
    @ResponseBody
    public ResponseEntity getGeneralSituationBy(@RequestParam("area") Set<Integer> areas) {  
        String g = casesTimelineRepository.getGeneralSituationBy(areas);
        return ResponseEntity.ok(g);
    }

}
