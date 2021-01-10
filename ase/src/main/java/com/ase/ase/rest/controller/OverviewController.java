package com.ase.ase.rest.controller;

import com.ase.ase.dao.CasesTimelineRepository;
import com.ase.ase.rest.response.Overview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(
        method = RequestMethod.GET,
        path = "/overview",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class OverviewController {

    @Autowired
    private CasesTimelineRepository casestimelineRepository;

    public ResponseEntity getOverview(){
        Overview o = casestimelineRepository.getOverview();
      return ResponseEntity.ok(o);
  }

}
