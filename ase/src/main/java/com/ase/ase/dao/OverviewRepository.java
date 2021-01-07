package com.ase.ase.dao;

import com.ase.ase.entities.Overview;
import com.ase.ase.rest.response.BasicInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OverviewRepository extends JpaRepository<Overview, Long> {
  @Query("SELECT new com.ase.ase.rest.response.BasicInformation(o.sumTested, o.sumCasesConfirmed,o.sumDeadConfirmed) FROM Overview o ")
    BasicInformation getBasic();
}
