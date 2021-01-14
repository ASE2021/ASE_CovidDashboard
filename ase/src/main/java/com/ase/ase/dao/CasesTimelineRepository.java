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
            ") order by time)) as item " +
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
            ") order by t.time)) as item " +
            "from cases_timeline t, population p " +
            "where t.area_id in :areas and t.area_id = p.id " +
            "group by t.area_id, t.area " +
            "order by t.area_id" +
            ") areaItem", nativeQuery = true)
    String getRelativeNewCasesBy(@Param("areas") Set<Integer> areas);

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', area_id, " +
                "'areaName', area, " +
                "'data', json_agg(json_build_object(" +
                    "'date', time, " +
                    "'values', array[" +
                        "json_build_object('identifier', 'deaths', 'value', new_dead)" +
                    "]" +
            ") order by time)) as item " +
            "from cases_timeline " +
            "where area_id in :areas " +
            "group by area_id, area " +
            "order by area_id" +
            ") areaItem", nativeQuery = true)
    String getNewDeathsBy(@Param("areas") Set<Integer> areas);

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', t.area_id, " +
                "'areaName', t.area, " +
                "'data', json_agg(json_build_object(" +
                    "'date', t.time, " +
                    "'values', array[" +
                        "json_build_object('identifier', 'deaths', 'value', t.new_dead/(p.population/100000.0))" +
                "]" +
            ") order by t.time)) as item " +
            "from cases_timeline t, population p " +
            "where t.area_id in :areas and t.area_id = p.id " +
            "group by t.area_id, t.area " +
            "order by t.area_id" +
            ") areaItem", nativeQuery = true)
    String getRelativeNewDeathsBy(@Param("areas") Set<Integer> areas);

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

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', ct.area_id, " +
                "'areaName', ct.area, " +
                "'data', json_agg(json_build_object(" +
                    "'date', ct.time, " +
                    "'values', array[" +
                        "json_build_object('identifier', 'activeCases', 'value', ct.sum_cases - ct.sum_dead - ct.sum_cured)," +
                        "json_build_object('identifier', 'newCases', 'value', ct.new_cases)," +
                        "json_build_object('identifier', 'cured', 'value', ct.new_cured)," +
                        "json_build_object('identifier', 'deaths', 'value', ct.new_dead)," +
                        "json_build_object('identifier', 'tests', 'value', case when t1.sum_tested is null then 0 else t2.sum_tested - t1.sum_tested end)," +
                        "json_build_object('identifier', 'intenseBeds', 'value', case when t2.usedib is null then 0 else t2.usedib end)," +
                        "json_build_object('identifier', 'normalBeds', 'value', case when t2.usednb is null then 0 else t2.usednb end)" +
                    "]" +
            ") order by ct.time )) as item " +
            "from cases_timeline ct " +
                "left join bed_and_test_timeline t2 on ct.time = t2.time " +
                "left join bed_and_test_timeline t1 on t1.time + interval '1 day' = t2.time " +
            "where ct.area_id in :areas " +
                "and (t2.area_id is null or ct.area_id = t2.area_id) " +
                "and (t1.area_id is null or ct.area_id = t1.area_id) " +
            "group by ct.area_id, ct.area " +
            "order by ct.area_id" +
            ") areaItem", nativeQuery = true)
    String getCasesBy(@Param("areas") Set<Integer> areas);

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', t.area_id, " +
                "'areaName', t.area, " +
                "'data', json_agg(json_build_object(" +
                    "'date', t.time, " +
                    "'values', array[" +
                        "json_build_object('identifier', 'deaths', 'value', ct2.new_dead)," +
                        "json_build_object('identifier', 'newCases', 'value', ct2.new_cases)," +
                        "json_build_object('identifier', 'hospitalisations', 'value', t.usedib + t.usednb)," +
                        "json_build_object('identifier', 'casesDifference', 'value', ct2.new_cases - ct1.new_cases)" +
                    "]" +
            ") order by t.time)) as item " +
            "from bed_and_test_timeline t, cases_timeline ct1, cases_timeline ct2 " +
            "where t.area_id in :areas " +
                "and t.area_id = ct1.area_id and ct1.time + interval '1 day' = ct2.time " +
                "and t.area_id = ct2.area_id and t.time = ct2.time " +
            "group by t.area_id, t.area " +
            "order by t.area_id" +
            ") areaItem", nativeQuery = true)
    String getGeneralSituationBy(@Param("areas") Set<Integer> areas);

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', t1.area_id, " +
                "'areaName', t1.area, " +
                "'data', json_agg(json_build_object(" +
                    "'date', t1.time, " +
                    "'values', array[" +
                        "json_build_object('identifier', 'activeCases', 'value', (ct.sum_cases - ct.sum_dead - ct.sum_cured)/(p.population/100000.0))," +
                        "json_build_object('identifier', 'newCases', 'value', ct.new_cases/(p.population/100000.0))," +
                        "json_build_object('identifier', 'cured', 'value', ct.new_cured/(p.population/100000.0))," +
                        "json_build_object('identifier', 'deaths', 'value', ct.new_dead/(p.population/100000.0))," +
                        "json_build_object('identifier', 'tests', 'value', (t2.sum_tested - t1.sum_tested)/(p.population/100000.0))" +
                "]" +
            ") order by t1.time)) as item " +
            "from bed_and_test_timeline t1, bed_and_test_timeline t2, cases_timeline ct , population p " +
            "where t1.area_id in :areas and t1.area_id = p.id and t1.area_id = t2.area_id and t1.time + interval '1 day' = t2.time  and t1.area_id = ct.area_id and t1.time = ct.time " +
            "group by t1.area_id, t1.area " +
            "order by t1.area_id" +
            ") areaItem", nativeQuery = true)
    String getRelativeCasesBy(@Param("areas") Set<Integer> areas);

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', area_id, " +
                "'areaName', area, " +
                "'data', array[" +
                    "json_build_object('identifier', 'activeCases', 'value', sum_cases - sum_dead - sum_cured)," +
                    "json_build_object('identifier', 'sumCases', 'value', sum_cases)," +
                    "json_build_object('identifier', 'sumCured', 'value', sum_cured)," +
                    "json_build_object('identifier', 'sumDeaths', 'value', sum_dead)" +
                "]) as item " +
            "from cases_timeline " +
            "where area_id < 10 " +
            "order by time desc, area_id " +
            "limit 9" +
            ") areaItem", nativeQuery = true)
    String getProvincesInfoBy();

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', t.area_id, " +
                "'areaName', t.area, " +
                "'data', array[" +
                    "json_build_object('identifier', 'activeCases', 'value', (t.sum_cases - t.sum_dead - t.sum_cured)/(p.population/100000.0))," +
                    "json_build_object('identifier', 'sumCases', 'value', t.sum_cases/(p.population/100000.0))," +
                    "json_build_object('identifier', 'sumCured', 'value', t.sum_cured/(p.population/100000.0))," +
                    "json_build_object('identifier', 'sumDeaths', 'value', t.sum_dead/(p.population/100000.0))" +
                "]) as item " +
            "from cases_timeline t , population p " +
            "where t.area_id < 10 and t.area_id = p.id " +
            "order by time desc, area_id " +
            "limit 9" +
            ") areaItem", nativeQuery = true)
    String getRelativeProvincesInfoBy();

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', area_id, " +
                "'areaName', area, " +
                "'data', array[" +
                    "json_build_object('identifier', 'activeCases', 'value', sum_cases - sum_dead - sum_cured)," +
                    "json_build_object('identifier', 'sumCases', 'value', sum_cases)," +
                    "json_build_object('identifier', 'sumCured', 'value', sum_cured)," +
                    "json_build_object('identifier', 'sumDeaths', 'value', sum_dead)" +
                "]) as item " +
            "from cases_timeline " +
            "where area_id > 10 " +
            "order by time desc, area_id " +
            "limit 94" +
            ") areaItem", nativeQuery = true)
    String getDistrictsInfoBy();

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', t.area_id, " +
                "'areaName', t.area, " +
                "'data', array[" +
                    "json_build_object('identifier', 'activeCases', 'value', (t.sum_cases - t.sum_dead - t.sum_cured)/(p.population/100000.0))," +
                    "json_build_object('identifier', 'sumCases', 'value', t.sum_cases/(p.population/100000.0))," +
                    "json_build_object('identifier', 'sumCured', 'value', t.sum_cured/(p.population/100000.0))," +
                    "json_build_object('identifier', 'sumDeaths', 'value', t.sum_dead/(p.population/100000.0))" +
                "]) as item " +
            "from cases_timeline t , population p " +
            "where t.area_id > 10 and t.area_id = p.id " +
            "order by time desc, area_id " +
            "limit 94" +
            ") areaItem", nativeQuery = true)
    String getRelativeDistrictsInfoBy();
}
