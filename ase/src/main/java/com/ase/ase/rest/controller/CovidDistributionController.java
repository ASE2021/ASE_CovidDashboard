package com.ase.ase.rest.controller;

import com.ase.ase.dao.SexAndAgeDistributionRepository;
import com.ase.ase.rest.response.SexDistribution;
import com.ase.ase.rest.response.SexDistributionPerProvinceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/distribution")
public class CovidDistributionController {

    @Autowired
    private SexAndAgeDistributionRepository sexAndAgeDistributionRepository;
  
    @CrossOrigin
    @GetMapping(value = "/sex?{area-id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity getSexDistributionBy(@PathVariable("area-id") Set<Integer> areas) {
        String g = sexAndAgeDistributionRepository.getSexDistributionBy(areas);
        return ResponseEntity.ok(g);
    }
    
    @CrossOrigin
    @GetMapping(value = "/age-sex/cases?{area-id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity getSexAndAgeCaseDistributionBy(@PathVariable("area-id") Set<Integer> areas) {
            String g = sexAndAgeDistributionRepository.getSexAndAgeCaseDistributionBy(areas);
        return ResponseEntity.ok(g);
    }

    @CrossOrigin
    @GetMapping(value = "/age-sex/deaths?{area-id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity getsexAndAgeDeathDistributionBy(@PathVariable("area-id") Set<Integer> areas) {
        String g = sexAndAgeDistributionRepository.getSexAndAgeDeathDistributionBy(areas);
        return ResponseEntity.ok(g);
    }

}
