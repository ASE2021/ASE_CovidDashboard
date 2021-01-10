package com.ase.ase.rest.controller;

import com.ase.ase.rest.response.InfoData;
import com.ase.ase.rest.response.AreaResponse;
import com.ase.ase.rest.response.InformationPerDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/comparison")
public class ComparisonCovidController {

    @CrossOrigin
    @GetMapping(value = "/cases", produces = "application/json")
    @ResponseBody
    public ResponseEntity<AreaResponse> listCasesComparisonFor(@RequestParam("area-id") List<Integer> areaId) {
        return ResponseEntity.ok(
                new AreaResponse(areaId, "Austria", Arrays.asList(
                        new InformationPerDate("10.10.2020", Arrays.asList(
                                new InfoData("deaths", 100),
                                new InfoData("cured", 40),
                                new InfoData("newCases", 20)))
                ))
        );
    }
}
