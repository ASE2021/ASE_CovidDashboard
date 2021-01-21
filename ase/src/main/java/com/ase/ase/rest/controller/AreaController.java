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
@RequestMapping("/area-info")
public class AreaController {
  
    @Autowired
    private CasesTimelineRepository casesTimelineRepository;

    @CrossOrigin
    @GetMapping(value = "/", produces = "application/json")
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
