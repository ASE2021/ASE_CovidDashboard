package com.ase.ase.dao;

import com.ase.ase.entities.AgeDistribution;
import com.ase.ase.entities.Austria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AustriaRepository extends JpaRepository<Austria, Long> {

    @Query("Select a.newCases, a.time from Austria a")
    List<Object[]> findAllNewCases();

    @Query("Select a.sumCases-a.sumCured-a.sumDead, a.time from Austria a")
    List<Object[]> findAllActiveCases();
}
