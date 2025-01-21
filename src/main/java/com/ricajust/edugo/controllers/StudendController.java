package com.ricajust.edugo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricajust.edugo.dtos.StudentDTO;
import com.ricajust.edugo.models.Student;
import com.ricajust.edugo.services.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudendController {

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
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student savedStudent = studentService.saveStudent(student);
		return ResponseEntity.status(201).body(savedStudent);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable UUID id, @RequestBody Student updatedStudent) {
		return studentService.getStudentById(id).map(existingStudent -> {
			updatedStudent.setId(existingStudent.getId());
			Student savedStudent = studentService.saveStudent(updatedStudent);
			return ResponseEntity.ok(savedStudent);
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
