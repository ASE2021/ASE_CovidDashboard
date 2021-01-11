package com.ase.ase.dao;

import com.ase.ase.entities.CasesTimeline;
import com.ase.ase.rest.response.CasesPerDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CasesTimelineRepository extends JpaRepository<CasesTimeline, Long> {
    @Query("SELECT new com.ase.ase.rest.response.CasesPerDate(t.time, t.newCases) FROM CasesTimeline t WHERE t.areaId = ?1")
    List<CasesPerDate> findAllBy(int areaId);

    @Query(value = "Select CAST(json_build_object('activeCases', sum_cases - sum_dead - sum_cured, " +
            "'sumDeaths', sum_dead, " +
            "'sumCases', sum_cases, " +
            "'sumCured', sum_cured) AS VARCHAR) " +
            "from cases_timeline where area_id = 10 order by time desc limit 1", nativeQuery = true)
    String getOverview();
}
