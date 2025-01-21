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

import com.ricajust.edugo.dtos.TeacherDTO;
import com.ricajust.edugo.models.Teacher;
import com.ricajust.edugo.services.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

	@Autowired
	private final TeacherService teacherService;

	@GetMapping
	public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
		List<TeacherDTO> teachers = teacherService.getAllTeachers();
		return ResponseEntity.ok(teachers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable UUID id) {
		return teacherService.getTeacherById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
		Teacher savedTeacher = teacherService.saveTeacher(teacher);
		return ResponseEntity.status(201).body(savedTeacher);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Teacher> updateTeacher(@PathVariable UUID id, @RequestBody Teacher updatedTeacher) {
		return teacherService.getTeacherById(id).map(existingTeacher -> {
			updatedTeacher.setId(existingTeacher.getId());
			Teacher savedTeacher = teacherService.saveTeacher(updatedTeacher);
			return ResponseEntity.ok(savedTeacher);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable UUID id) {
		if (teacherService.getTeacherById(id).isPresent()) {
			teacherService.deleteTeacherById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
