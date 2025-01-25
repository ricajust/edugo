package com.ricajust.edugo.repositories;

import java.util.UUID;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricajust.edugo.models.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

	// @Query("SELECT obj FROM Grade obj WHERE obj.studentId == id")
	List<Grade> findByStudentId(UUID id);
}
