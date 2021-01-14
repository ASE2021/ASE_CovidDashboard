package com.ase.ase.rest.controller;

import com.ase.ase.dao.CasesTimelineRepository;
import com.ase.ase.rest.response.Overview;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/overview")
public class OverviewController {

    @GetMapping(value = "/overview", produces = "application/json")
    @ResponseBody

    public ResponseEntity getOverview(){
        Overview o = CasesTimelineRepository.getOverview();
      return ResponseEntity.ok(o);
  }

}
