package com.ase.ase.dao;

import com.ase.ase.entities.AgeDistribution;
import com.ase.ase.entities.Districts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictsRepository extends JpaRepository<Districts, Long> {
}
