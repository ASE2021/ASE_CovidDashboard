package com.ase.ase.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.ase.ase.dao.CasesTimelineRepository;
import com.ase.ase.dao.PopulationRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class OverviewController {
   
    @Autowired
    private CasesTimelineRepository casesTimelineRepository;
    private PopulationRepository populationRepository;
    
    @CrossOrigin
    @GetMapping(value = "/overview", produces = "application/json")
    @ResponseBody
    public ResponseEntity getOverview(){
        String o = casesTimelineRepository.getOverview();
            return ResponseEntity.ok(o);
    }

    @CrossOrigin
    @GetMapping(value = "/provinces", produces = "application/json")
    @ResponseBody
    public ResponseEntity getAllProvincesAndAustria(){
        String p = populationRepository.getAllProvincesAndAustria();
            return ResponseEntity.ok(p);
            
    }

    @CrossOrigin
    @GetMapping(value = "/districs", produces = "application/json")
    @ResponseBody
    public ResponseEntity getAllDistricts(){
        String d = populationRepository.getAllDistricts();
            return ResponseEntity.ok(d);
    }
    
}
