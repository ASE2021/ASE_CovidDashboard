package com.ase.ase.rest.controller;
import com.ase.ase.dao.BedAndTestTimelineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@CrossOrigin("*")
@RestController
public class HospitalController {

    @Autowired
    private BedAndTestTimelineRepository bedandTestTimelineRepository;

    @CrossOrigin
    @GetMapping(value = "/hospital-bed-utilizations/", produces = "application/json")
    @ResponseBody
    public ResponseEntity getBedUtilizationBy(@RequestParam("area") Set<Integer> areas, @RequestParam("type") int type) {
        String b = bedandTestTimelineRepository.getBedUtilizationBy(areas,type);
        return ResponseEntity.ok(b);
    }

    @CrossOrigin
    @GetMapping(value = "/daily/hospital/", produces = "application/json") 
    @ResponseBody
    public ResponseEntity getHospitalisationsBy(@RequestParam("area") Set<Integer> areas) {
        String g = bedandTestTimelineRepository.getHospitalisationsBy(areas);
        return ResponseEntity.ok(g);
    }
}
