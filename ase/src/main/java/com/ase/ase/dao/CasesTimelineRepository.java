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
 
}
