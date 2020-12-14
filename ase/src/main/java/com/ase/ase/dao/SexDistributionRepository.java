package com.ase.ase.dao;

import com.ase.ase.entities.AgeDistribution;
import com.ase.ase.entities.SexDistribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexDistributionRepository extends JpaRepository<SexDistribution, Long> {
}
