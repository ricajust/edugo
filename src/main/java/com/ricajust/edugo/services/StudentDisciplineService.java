package com.ricajust.edugo.services;

import com.ricajust.edugo.dtos.StudentDisciplineDTO;
import com.ricajust.edugo.models.StudentDiscipline;
import com.ricajust.edugo.repositories.StudentDisciplineRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class StudentDisciplineService {

    @Autowired
    private final StudentDisciplineRepository studentDisciplineRepository;

    private static final Logger logger = Logger.getLogger(StudentDisciplineService.class.getName());

    public List<StudentDisciplineDTO> getStudentDisciplines(UUID studentId) {
        List<StudentDiscipline> studentDisciplines = studentDisciplineRepository.findByStudentId(studentId);
        return studentDisciplines.stream()
            .map(StudentDisciplineDTO::new)
            .collect(Collectors.toList());
    }

    public StudentDiscipline addAbsences(UUID studentId, Long disciplineId, int absences) {
        logger.info("Adding absences for studentId: " + studentId + ", disciplineId: " + disciplineId + ", absences: " + absences);

        StudentDiscipline studentDiscipline = studentDisciplineRepository
                .findByStudentIdAndDisciplineId(studentId, disciplineId)
                .orElseThrow(() -> new IllegalArgumentException("Relationship not found"));

        studentDiscipline.setAbsences(studentDiscipline.getAbsences() + absences);
        return studentDisciplineRepository.save(studentDiscipline);
    }
}
