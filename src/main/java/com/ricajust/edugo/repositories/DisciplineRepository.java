package com.ricajust.edugo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricajust.edugo.models.Discipline;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long>{

}
