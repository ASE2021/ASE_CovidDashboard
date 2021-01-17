package com.ase.ase.rest.controller;
import com.ase.ase.dao.BedAndTestTimelineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin("*")
@RestController
@RequestMapping("/hospital-bed-utilizations")
public class HospitalController {

    @Autowired
    private BedAndTestTimelineRepository bedandTestTimelineRepository;

    @CrossOrigin
    @GetMapping(value = "?{area-id}&{type}", produces = "application/json")
    @ResponseBody
    public ResponseEntity getBedUtilizationBy(@PathVariable("area-id") Set<Integer> areas, @PathVariable("type") int type) {
        String b = bedandTestTimelineRepository.getBedUtilizationBy(areas,type);
        return ResponseEntity.ok(b);
    }
}
