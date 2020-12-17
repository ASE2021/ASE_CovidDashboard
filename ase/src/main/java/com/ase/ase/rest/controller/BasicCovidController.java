package com.ase.ase.rest.controller;

import com.ase.ase.dao.TimelineRepository;
import com.ase.ase.rest.response.CasesPerDate;
import com.ase.ase.rest.response.DailyCasesPerProvinceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/daily")
public class BasicCovidController {

    @Autowired
    private TimelineRepository timelineRepository;

    @CrossOrigin
    @GetMapping(value = "/{province-id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<DailyCasesPerProvinceDto> listNewCasesFor(@PathVariable("province-id") int provinceId) {
        return ResponseEntity.ok(
                new DailyCasesPerProvinceDto(
                        provinceId,
                        timelineRepository.findAllBy(provinceId)));
    }

}
