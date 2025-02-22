package com.ricajust.edugo.controllers;

import com.ricajust.edugo.dtos.AddAbsencesRequestDTO;
import com.ricajust.edugo.dtos.StudentDisciplineDTO;
import com.ricajust.edugo.models.StudentDiscipline;
import com.ricajust.edugo.services.StudentDisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student-discipline")
@RequiredArgsConstructor
public class StudentDisciplineController {

    private final StudentDisciplineService studentDisciplineService;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<StudentDisciplineDTO>> getStudentDisciplines(@PathVariable UUID studentId) {
        return ResponseEntity.ok(studentDisciplineService.getStudentDisciplines(studentId));
    }

    @PostMapping("/absences")
    public ResponseEntity<StudentDiscipline> addAbsences(@RequestBody AddAbsencesRequestDTO request) {
        return ResponseEntity.ok(studentDisciplineService.addAbsences(request.getStudentId(), request.getDisciplineId(), request.getAbsences()));
    }
}
