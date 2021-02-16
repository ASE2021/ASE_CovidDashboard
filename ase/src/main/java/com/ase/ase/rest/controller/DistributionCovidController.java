package com.ase.ase.rest.controller;

import com.ase.ase.dao.SexAndAgeDistributionRepository;
import com.ase.ase.rest.response.SexDistribution;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/distribution")
public class DistributionCovidController {

    @Autowired
    private SexAndAgeDistributionRepository sexAndAgeDistributionRepository;
  
    @CrossOrigin
    @GetMapping(value = "/sex", produces = "application/json")
    @ResponseBody
    public ResponseEntity getSexDistributionBy(@RequestParam("area") Set<Integer> areas) {
        return ResponseEntity.ok(sexAndAgeDistributionRepository.getSexDistributionBy(areas));
    }
    
    @CrossOrigin
    @GetMapping(value = "/age-sex/cases", produces = "application/json")
    @ResponseBody
    public ResponseEntity getSexAndAgeCaseDistributionBy(@RequestParam("area") Set<Integer> areas) {
        return ResponseEntity.ok(sexAndAgeDistributionRepository.getSexAndAgeCaseDistributionBy(areas));
    }

    @CrossOrigin
    @GetMapping(value = "/age-sex/cured", produces = "application/json")
    @ResponseBody
    public ResponseEntity getSexAndAgeCureDistributionBy(@RequestParam("area") Set<Integer> areas) {
        return ResponseEntity.ok(sexAndAgeDistributionRepository.getSexAndAgeCureDistributionBy(areas));
    }

    @CrossOrigin
    @GetMapping(value = "/age-sex/deaths", produces = "application/json")
    @ResponseBody
    public ResponseEntity getSexAndAgeDeathDistributionBy(@RequestParam("area") Set<Integer> areas) {
        return ResponseEntity.ok(sexAndAgeDistributionRepository.getSexAndAgeDeathDistributionBy(areas));
    }

}
