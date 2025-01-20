package com.ricajust.edugo.controllers;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

import com.ricajust.edugo.models.Discipline;
import com.ricajust.edugo.services.DisciplineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/disciplines")
@RequiredArgsConstructor
public class DisciplineController {
	@Autowired
	private DisciplineService service;

	@GetMapping
	public ResponseEntity<List<Discipline>> getAllDiscipline() {
		List<Discipline> disciplines = service.getAllDisciplines();
		if (disciplines.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
		}
		return ResponseEntity.ok().body(disciplines);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) {
		Discipline discipline = service.getDisciplineByid(id);
		return ResponseEntity.ok().body(discipline);
	}

	@PostMapping
	public ResponseEntity<Discipline> saveDiscipline(@RequestBody Discipline discipline) {
		discipline = service.saveDiscipline(discipline);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(discipline.getId()).toUri();
		// Retorna o status 201 com o header "Location"
		return ResponseEntity.created(uri).build();
	}
}
