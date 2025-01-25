package com.ricajust.edugo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ricajust.edugo.dtos.StudentDTO;
import com.ricajust.edugo.models.Student;
import com.ricajust.edugo.services.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable UUID id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO savedStudentDTO = studentService.saveStudent(studentDTO);
        return ResponseEntity.status(201).body(savedStudentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable UUID id, @RequestBody StudentDTO updatedStudentDTO) {
        return studentService.getStudentById(id).map(existingStudent -> {
            updatedStudentDTO.setId(existingStudent.getId());
            StudentDTO savedStudentDTO = studentService.saveStudent(updatedStudentDTO);
            return ResponseEntity.ok(savedStudentDTO);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        if (studentService.getStudentById(id).isPresent()) {
            studentService.deleteStudentById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}