package com.ase.ase.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.ase.ase.dao.CasesTimelineRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
public class OverviewController {
   
    @Autowired
    private CasesTimelineRepository casesTimelineRepository;
    
    @CrossOrigin
    @GetMapping(value = "/overview", produces = "application/json")
    @ResponseBody
    public ResponseEntity getOverview(){
        String o = casesTimelineRepository.getOverview();
            return ResponseEntity.ok(o);
    }

    @CrossOrigin
    @GetMapping(value = "/area-info/", produces = "application/json")
    @ResponseBody
    public ResponseEntity getRelativeAreaInfoBy(@RequestParam("area") Set<Integer> areas, @RequestParam("relative") boolean relative){
        String o ="";
        if(relative == true){
            o = casesTimelineRepository.getRelativeAreaInfoBy(areas);
        } else {
            o = casesTimelineRepository.getAreaInfoBy(areas);
        }
        return ResponseEntity.ok(o);
    }
    
}
