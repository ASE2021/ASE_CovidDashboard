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

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
            "'areaId', t1.area_id, " +
            "'areaName', t1.area, " +
            "'data', json_agg(json_build_object(" +
            "'date', t1.time, " +
            "'values', array[" +
            "json_build_object('identifier', 'tests', 'value', t2.sum_tested - t1.sum_tested)" +
            "]" +
            ") order by t1.time)) as item " +
            "from bed_and_test_timeline t1, bed_and_test_timeline t2 " +
            "where t1.area_id in :areas and t1.area_id = t2.area_id and t1.time + interval '1 day' = t2.time " +
            "group by t1.area_id, t1.area " +
            "order by t1.area_id" +
            ") areaItem", nativeQuery = true)
    String getNewTestsBy(@Param("areas") Set<Integer> areas);

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
            "'areaId', t1.area_id, " +
            "'areaName', t1.area, " +
            "'data', json_agg(json_build_object(" +
            "'date', t1.time, " +
            "'values', array[" +
            "json_build_object('identifier', 'tests', 'value', (t2.sum_tested - t1.sum_tested)/(p.population/100000.0))" +
            "]" +
            ") order by t1.time)) as item " +
            "from bed_and_test_timeline t1, bed_and_test_timeline t2, population p " +
            "where t1.area_id in :areas and t1.area_id = p.id and t1.area_id = t2.area_id and t1.time + interval '1 day' = t2.time " +
            "group by t1.area_id, t1.area " +
            "order by t1.area_id" +
            ") areaItem", nativeQuery = true)
    String getRelativeNewTestsBy(@Param("areas") Set<Integer> areas);
}
