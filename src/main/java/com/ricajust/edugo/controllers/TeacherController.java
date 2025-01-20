package com.ricajust.edugo.controllers;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricajust.edugo.models.Teacher;
import com.ricajust.edugo.services.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

	@Autowired
	private TeacherService service;
	
	@GetMapping
	public ResponseEntity<List<Teacher>> getAllTeachers(){
		List<Teacher> teachers = service.getAllTeachers();
		if (teachers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
		}
		return ResponseEntity.ok().body(teachers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Teacher> getTeacherById(@PathVariable UUID id) {
		Teacher teacher = service.getTeacherById(id);
		return ResponseEntity.ok().body(teacher);
	}

	@PostMapping
	public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher){
		teacher = service.saveTeacher(teacher);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacher.getId()).toUri();
		// Retorna o status 201 com o header "Location"
		return ResponseEntity.created(uri).build();	}
}
