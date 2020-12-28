package com.ase.ase.rest.controller;

import com.ase.ase.rest.response.SexDistribution;
import com.ase.ase.rest.response.SexDistributionPerProvinceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin("*")
@RestController
@RequestMapping("/distribution")
public class CovidDistributionController {

    @CrossOrigin
    @GetMapping(value = "/sex/{province-id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<SexDistributionPerProvinceDto> listSexDistributionFor(@PathVariable("province-id") int provinceId) {
        return ResponseEntity.ok(
                new SexDistributionPerProvinceDto(provinceId, Arrays.asList(
                        new SexDistribution[]{new SexDistribution(100, 150)}
                ))
        );
    }
}
