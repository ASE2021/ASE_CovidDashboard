package com.ase.ase.rest.controller;

import com.ase.ase.dao.BedAndTestTimelineRepository;
import com.ase.ase.dao.CasesTimelineRepository;
import com.ase.ase.entities.BedAndTestTimeline;
import com.ase.ase.rest.response.DailyCasesPerProvinceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/daily")
public class BasicCovidController {

  @Autowired
  private CasesTimelineRepository casesTimelineRepository;
  @Autowired
  private BedAndTestTimelineRepository bedAndTestTimelineRepository;

  @CrossOrigin
  @GetMapping(value = "/{province-id}", produces = "application/json")
  @ResponseBody
  public ResponseEntity<DailyCasesPerProvinceDto> listNewCasesFor(@PathVariable("province-id") int provinceId) {
    return ResponseEntity.ok(
        new DailyCasesPerProvinceDto(
            provinceId,
            casesTimelineRepository.findAllBy(provinceId)));
  }

  @CrossOrigin
  @GetMapping(value = "/new-cases", produces = "application/json")
  @ResponseBody
  public ResponseEntity getRelativeNewCasesBy(
      @RequestParam("area") Set<Integer> areas,
      @RequestParam(value = "relative", defaultValue = "false", required = true) boolean relative) {
    String o = "";
    if (relative == true) {
      o = casesTimelineRepository.getRelativeNewCasesBy(areas);
    } else {
      o = casesTimelineRepository.getNewCasesBy(areas);
    }
    return ResponseEntity.ok(o);
  }

  @CrossOrigin
  @GetMapping(value = "/deaths", produces = "application/json")
  @ResponseBody
  public ResponseEntity getRelativeNewDeathsBy(
      @RequestParam("area") Set<Integer> areas,
      @RequestParam(value = "relative", defaultValue = "false", required = true) boolean relative) {
    String o = "";
    if (relative == true) {
      o = casesTimelineRepository.getRelativeNewDeathsBy(areas);
    } else {
      o = casesTimelineRepository.getNewDeathsBy(areas);
    }
    return ResponseEntity.ok(o);
  }

  @CrossOrigin
  @GetMapping(value = "/tests", produces = "application/json")
  @ResponseBody
  public ResponseEntity getRelativeNewTestsBy(
      @RequestParam("area") Set<Integer> areas,
      @RequestParam(value = "relative", defaultValue = "false", required = true) boolean relative) {
    String o = "";
    if (relative == true) {
      o = bedAndTestTimelineRepository.getRelativeNewTestsBy(areas);
    } else {
      o = bedAndTestTimelineRepository.getNewTestsBy(areas);
    }
    return ResponseEntity.ok(o);
  }

  @CrossOrigin
  @GetMapping(value = "/cases", produces = "application/json")
  @ResponseBody
  public ResponseEntity getCasesBy(
      @RequestParam("area") Set<Integer> areas,
      @RequestParam(value = "relative", defaultValue = "false") boolean relative) {
    if (relative) {
      return ResponseEntity.ok(casesTimelineRepository.getRelativeCasesBy(areas));
    }
    return ResponseEntity.ok(casesTimelineRepository.getCasesBy(areas));
  }

  @CrossOrigin
  @GetMapping(value = "/generalSituation", produces = "application/json")
  @ResponseBody
  public ResponseEntity getGeneralSituationBy(@RequestParam("area") Set<Integer> areas) {
    return ResponseEntity.ok(casesTimelineRepository.getGeneralSituationBy(areas));
  }
}
