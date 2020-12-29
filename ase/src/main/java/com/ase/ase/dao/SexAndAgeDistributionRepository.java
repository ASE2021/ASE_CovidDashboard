package com.ase.ase.dao;

import com.ase.ase.entities.SexDistribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexAndAgeDistributionRepository extends JpaRepository<SexDistribution, Long> {
}
