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

@Service
@RequiredArgsConstructor
public class StudentDisciplineService {

    @Autowired
    private final StudentDisciplineRepository studentDisciplineRepository;

    // public List<StudentDiscipline> getStudentDisciplines(UUID studentId) {
    //     return studentDisciplineRepository.findByStudentId(studentId);
    // }

    public List<StudentDisciplineDTO> getStudentDisciplines(UUID studentId) {
        List<StudentDiscipline> studentDisciplines = studentDisciplineRepository.findByStudentId(studentId);
        return studentDisciplines.stream()
            .map(StudentDisciplineDTO::new)
            .collect(Collectors.toList());
    }

    public StudentDiscipline addAbsences(UUID studentId, Long disciplineId, int absences) {
        StudentDiscipline studentDiscipline = studentDisciplineRepository
                .findByStudentIdAndDisciplineId(studentId, disciplineId)
                .orElseThrow(() -> new IllegalArgumentException("Relationship not found"));

        studentDiscipline.setAbsences(studentDiscipline.getAbsences() + absences);
        return studentDisciplineRepository.save(studentDiscipline);
    }
}
