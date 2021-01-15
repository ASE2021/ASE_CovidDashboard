package com.ase.ase.dao;

import com.ase.ase.entities.BedAndTestTimeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BedAndTestTimelineRepository extends JpaRepository<BedAndTestTimeline, Long> {

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', area_id, " +
                "'areaName', area, " +
                "'data', json_agg(json_build_object(" +
                    "'date', time, " +
                    "'values', array[" +
                        "json_build_object('identifier', 'intenseBeds', 'value', usedib)," +
                        "json_build_object('identifier', 'normalBeds', 'value', usednb)" +
                        "]" +
                ") order by time)) as item " +
            "from bed_and_test_timeline " +
            "where area_id in :areas " +
            "group by area_id, area " +
            "order by area_id" +
            ") areaItem", nativeQuery = true)
    String getHospitalisationsBy(@Param("areas") Set<Integer> areas);

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', area_id, " +
                "'areaName', area, " +
                "'data', json_agg(json_build_object(" +
                    "'date', time, " +
                    "'values', array[" +
                        "json_build_object('identifier', 'availableBeds', 'value', case when :type = 0 then freeib when :type = 1 then freenb else freeib + freenb end)," +
                        "json_build_object('identifier', 'utilizedBeds', 'value', case when :type = 0 then usedib when :type = 1 then usednb else usedib + usednb end)" +
                        "]" +
                ") order by time)) as item " +
            "from bed_and_test_timeline " +
            "where area_id in :areas " +
            "group by area_id, area " +
            "order by area_id" +
            ") areaItem", nativeQuery = true)
    String getBedUtilizationBy(@Param("areas") Set<Integer> areas, @Param("type") int type);
}
