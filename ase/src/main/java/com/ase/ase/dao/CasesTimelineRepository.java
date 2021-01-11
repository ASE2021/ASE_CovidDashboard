package com.ase.ase.dao;

import com.ase.ase.entities.CasesTimeline;
import com.ase.ase.rest.response.CasesPerDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CasesTimelineRepository extends JpaRepository<CasesTimeline, Long> {
    @Query("SELECT new com.ase.ase.rest.response.CasesPerDate(t.time, t.newCases) FROM CasesTimeline t WHERE t.areaId = ?1")
    List<CasesPerDate> findAllBy(int areaId);

    @Query(value = "Select CAST(json_build_object('activeCases', sum_cases - sum_dead - sum_cured, " +
            "'sumDeaths', sum_dead, " +
            "'sumCases', sum_cases, " +
            "'sumCured', sum_cured) AS VARCHAR) " +
            "from cases_timeline where area_id = 10 order by time desc limit 1", nativeQuery = true)
    String getOverview();

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', area_id, " +
                "'areaName', area, " +
                "'data', json_agg(json_build_object(" +
                    "'date', time, " +
                    "'values', array[" +
                        "json_build_object('identifier', 'newCases', 'value', new_cases)" +
                    "]" +
            "))) as item " +
            "from cases_timeline " +
            "where area_id in :areas " +
            "group by area_id, area " +
            "order by area_id" +
            ") areaItem", nativeQuery = true)
    String getNewCasesBy(@Param("areas") Set<Integer> areas);

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', t.area_id, " +
                "'areaName', t.area, " +
                "'data', json_agg(json_build_object(" +
                    "'date', t.time, " +
                    "'values', array[" +
                        "json_build_object('identifier', 'newCases', 'value', t.new_cases/(p.population/100000.0))" +
                "]" +
            "))) as item " +
            "from cases_timeline t, population p " +
            "where t.area_id in :areas and t.area_id = p.id " +
            "group by t.area_id, area " +
            "order by t.area_id" +
            ") areaItem", nativeQuery = true)
    String getRelativeNewCasesBy(@Param("areas") Set<Integer> areas);
}
