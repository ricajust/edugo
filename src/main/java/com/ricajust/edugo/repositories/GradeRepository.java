package com.ricajust.edugo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricajust.edugo.models.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

}
