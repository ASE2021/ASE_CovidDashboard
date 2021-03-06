package com.ase.ase.dao;

import com.ase.ase.entities.SexAndAgeDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SexAndAgeDistributionRepository extends JpaRepository<SexAndAgeDistribution, Long> {

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "select json_build_object('areaId', dataItems.area_id, 'areaName', dataItems.area, 'data', json_agg(dataItems.dataItem)) as item " +
            "from (" +
                "select " +
                    "area_id, " +
                    "area, " +
                    "json_build_object(" +
                        "'ageIntervalId', age_interval_id, " +
                        "'ageInterval', age_interval, " +
                        "'values', json_agg(case when sex = 'M' " +
                            "then json_build_object('identifier', 'maleCases', 'value', sum_cases) " +
                            "else json_build_object('identifier', 'femaleCases', 'value', sum_cases) " +
                            "end)" +
                    ") as dataItem " +
                "from sex_and_age_distribution " +
                "group by area_id, area, age_interval_id, age_interval " +
                "order by area_id, age_interval_id" +
            ") dataItems " +
            "where area_id in :areas " +
            "group by dataItems.area_id, dataItems.area " +
            ") areaItems", nativeQuery = true)
    String getSexAndAgeCaseDistributionBy(@Param("areas")Set<Integer> areas);

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "select json_build_object('areaId', dataItems.area_id, 'areaName', dataItems.area, 'data', json_agg(dataItems.dataItem)) as item " +
            "from (" +
                "select " +
                    "area_id, " +
                    "area, " +
                    "json_build_object(" +
                        "'ageIntervalId', age_interval_id, " +
                        "'ageInterval', age_interval, " +
                        "'values', json_agg(case when sex = 'M' " +
                            "then json_build_object('identifier', 'maleDeaths', 'value', sum_dead) " +
                            "else json_build_object('identifier', 'femaleDeaths', 'value', sum_dead) " +
                            "end)" +
                    ") as dataItem " +
                "from sex_and_age_distribution " +
                "group by area_id, area, age_interval_id, age_interval " +
                "order by area_id, age_interval_id" +
            ") dataItems " +
            "where area_id in :areas " +
            "group by dataItems.area_id, dataItems.area " +
            ") areaItems", nativeQuery = true)
    String getSexAndAgeDeathDistributionBy(@Param("areas")Set<Integer> areas);

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "select json_build_object('areaId', dataItems.area_id, 'areaName', dataItems.area, 'data', json_agg(dataItems.dataItem)) as item " +
            "from (" +
                "select " +
                    "area_id, " +
                    "area, " +
                    "json_build_object(" +
                        "'ageIntervalId', age_interval_id, " +
                        "'ageInterval', age_interval, " +
                        "'values', json_agg(case when sex = 'M' " +
                            "then json_build_object('identifier', 'maleCured', 'value', sum_cured) " +
                            "else json_build_object('identifier', 'femaleCured', 'value', sum_cured) " +
                            "end)" +
                    ") as dataItem " +
                "from sex_and_age_distribution " +
                "group by area_id, area, age_interval_id, age_interval " +
                "order by area_id, age_interval_id" +
            ") dataItems " +
            "where area_id in :areas " +
            "group by dataItems.area_id, dataItems.area " +
            ") areaItems", nativeQuery = true)
    String getSexAndAgeCureDistributionBy(@Param("areas")Set<Integer> areas);
    
    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "select json_build_object('areaId', dataItems.area_id, 'areaName', dataItems.area, 'data', json_agg(dataItems.dataItem)) as item " +
            "from (" +
                "select " +
                    "area_id, " +
                    "area, " +
                    "json_build_object(" +
                        "'sex', sex, " +
                        "'values', array[" +
                            "json_build_object('identifier', 'cured', 'value', sum(sum_cured)), " +
                            "json_build_object('identifier', 'dead', 'value', sum(sum_dead)) " +
                    "]) as dataItem " +
                "from sex_and_age_distribution " +
                "group by area_id, area, sex " +
                "order by area_id" +
            ") dataItems " +
            "where area_id in :areas " +
            "group by dataItems.area_id, dataItems.area " +
            "order by dataItems.area_id" +
            ") areaItems", nativeQuery = true)
    String getSexDistributionBy(@Param("areas")Set<Integer> areas);
}
