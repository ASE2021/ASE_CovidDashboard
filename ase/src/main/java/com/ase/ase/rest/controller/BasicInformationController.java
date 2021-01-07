package com.ase.ase.rest.controller;

import com.ase.ase.dao.OverviewRepository;
import com.ase.ase.rest.response.BasicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(
        method = RequestMethod.GET,
        path = "/basicinformation",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class BasicInformationController {

    @Autowired
    private OverviewRepository overviewRepository;

    public ResponseEntity getBasicInformation(){
        BasicInformation b = overviewRepository.getBasic();
      return ResponseEntity.ok(b);
  }

}
