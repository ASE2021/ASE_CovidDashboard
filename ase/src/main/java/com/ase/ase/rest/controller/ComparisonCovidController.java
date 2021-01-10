package com.ase.ase.rest.controller;

import com.ase.ase.rest.response.ValuesForREST;
import com.ase.ase.rest.response.ValuesForRESTPerAreaDto;
import com.ase.ase.rest.response.ValuesForRESTPerDate;
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
    public ResponseEntity<ValuesForRESTPerAreaDto> listCasesComparisonFor(@RequestParam("area-id") List<Integer> areaId) {
        return ResponseEntity.ok(
                new ValuesForRESTPerAreaDto(areaId, "Austria", Arrays.asList(
                        new ValuesForRESTPerDate("10.10.2020", Arrays.asList(
                                new ValuesForREST("deaths", 100),
                                new ValuesForREST("cured", 40),
                                new ValuesForREST("newCases", 20)))
                ))
        );
    }
}
