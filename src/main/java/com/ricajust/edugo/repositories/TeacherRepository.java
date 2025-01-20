package com.ricajust.edugo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricajust.edugo.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID>{

}
