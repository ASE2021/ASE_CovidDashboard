package com.ase.ase;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequestMapping(path = "/covids")
public class CovidController {
    @Autowired
    private CovidDAO covidDao;

    @GetMapping(path="/", produces = "application/json")
    public Covids getCovids()
    {
        return covidDao.getAllCovids();
    }

    @GetMapping(path="/", produces = "application/json")
    public Covids getactiveCases()
    {
        return covidDao.getActiveCases();
    }

    @GetMapping(path="/", produces = "application/json")
    public Covids gettotalCases()
    {
        return covidDao.getTotalCases();
    }

    @GetMapping(path="/", produces = "application/json")
    public Covids getDeaths()
    {
        return covidDao.getDeaths();
    }

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addCovid(@RequestBody Covid covid)
    {
       /* Integer id = covidDao.getAllCovids().getCovidList().size() + 1;
        //covid.set(id);

        covidDao.addCovid(covid);
*/
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{totalCases}")
                .buildAndExpand(covid.getCount())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
