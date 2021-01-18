package com.ase.ase.rest.controller;

import com.ase.ase.rest.response.SexDistribution;
import com.ase.ase.rest.response.DailySexDistributionPerProvinceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin("*")
@RestController
@RequestMapping("/distribution")
public class DistributionCovidController {

    @CrossOrigin
    @GetMapping(value = "/sex/{province-id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<DailySexDistributionPerProvinceDto> listSexDistributionFor(@PathVariable("province-id") int provinceId) {
        return ResponseEntity.ok(
                new DailySexDistributionPerProvinceDto(provinceId, Arrays.asList(
                        new SexDistribution(100, 150, 4, 2)
                ))
        );
    }
}
