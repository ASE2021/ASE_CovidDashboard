package com.ase.ase.dao;

import com.ase.ase.entities.CasesTimeline;
import com.ase.ase.rest.response.CasesPerDate;
import com.ase.ase.rest.response.Overview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CasesTimelineRepository extends JpaRepository<CasesTimeline, Long> {
    @Query("SELECT new com.ase.ase.rest.response.CasesPerDate(t.time, t.newCases) FROM CasesTimeline t WHERE t.areaId = ?1")
    List<CasesPerDate> findAllBy(int areaId);
    //@Query(value = "SELECT b.sum_tested, c.sum_cases, c.sum_cases - c.sum_cured - c.sum_dead as current_sick, c.sum_dead FROM bed_and_test_timeline b, cases_timeline  c WHERE b.area_id = 10 and c.area_id = 10 and c.time = b.time order by b.time desc limit 1", nativeQuery = true)
    @Query(value = "SELECT new com.ase.ase.rest.response.Overview(c.sum_cases - c.sum_cured - c.sum_dead as current_sick, c.sum_dead, c.sum_cases, c.sum_cured)  FROM bed_and_test_timeline b, cases_timeline  c WHERE b.area_id = 10 and c.area_id = 10 and c.time = b.time order by b.time desc limit 1", nativeQuery = true)
    Overview getOverview();
}
