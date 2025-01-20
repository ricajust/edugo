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

import com.ricajust.edugo.models.Student;
import com.ricajust.edugo.services.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudendController {

	@Autowired
	private StudentService service;
	
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> students = service.getAllStudents();
		if (students.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
		}
		return ResponseEntity.ok().body(students);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable UUID id){
		Student student = service.getStudentById(id);
		return ResponseEntity.ok().body(student);
	}

	@PostMapping
	public ResponseEntity<Student> saveStudent(@RequestBody Student student){
		student = service.saveStudent(student);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId()).toUri();
		// Retorna o status 201 com o header "Location"
		return ResponseEntity.created(uri).build();
	}
}
