package com.ase.ase.dao;

import com.ase.ase.entities.Timeline;
import com.ase.ase.rest.response.CasesPerDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    @Query("SELECT new com.ase.ase.rest.response.CasesPerDate(t.time, t.newCases) FROM Timeline t WHERE t.areaId = ?1")
    List<CasesPerDate> findAllBy(int areaId);
}
