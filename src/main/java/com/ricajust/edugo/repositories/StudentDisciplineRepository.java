package com.ricajust.edugo.repositories;

import com.ricajust.edugo.models.StudentDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentDisciplineRepository extends JpaRepository<StudentDiscipline, Long> {

    // Busca por uma associação específica entre Student e Discipline
    Optional<StudentDiscipline> findByStudentIdAndDisciplineId(UUID studentId, Long disciplineId);

    // Busca todas as associações para um estudante específico
    List<StudentDiscipline> findByStudentId(UUID studentId);
}
