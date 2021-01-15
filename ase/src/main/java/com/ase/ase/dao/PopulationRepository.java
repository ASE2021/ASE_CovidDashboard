package com.ase.ase.dao;

import com.ase.ase.entities.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PopulationRepository extends JpaRepository<Population, Long> {

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', id, " +
                "'areaName', name) as item " +
            "from population " +
            "where id <= 10 " +
            "order by id " +
            ") areaItem", nativeQuery = true)
    String getAllProvincesAndAustria();

    @Query(value = "Select CAST(json_build_object('items', json_agg(item)) AS VARCHAR) from (" +
            "Select json_build_object(" +
                "'areaId', id, " +
                "'areaName', name) as item " +
            "from population " +
            "where id > 10 " +
            "order by id " +
            ") areaItem", nativeQuery = true)
    String getAllDistricts();
}
