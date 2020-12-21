package com.ase.ase.dao;

import com.ase.ase.entities.Population;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopulationRepository extends JpaRepository<Population, Long> {
}
