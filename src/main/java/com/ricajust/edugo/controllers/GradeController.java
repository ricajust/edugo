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

import com.ricajust.edugo.dtos.GradeByStudentDTO;
import com.ricajust.edugo.dtos.GradeDTO;
import com.ricajust.edugo.models.Grade;
import com.ricajust.edugo.services.GradeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {

	@Autowired
	private final GradeService gradeService;

	@GetMapping
	public ResponseEntity<List<GradeDTO>> getAllGrades() {
		List<GradeDTO> grades = gradeService.getAllGrades();
		return ResponseEntity.ok(grades);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GradeDTO> getGradeById(@PathVariable Long id) {
		return gradeService.getGradeById(id)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}
	

	 // Endpoint para buscar todas as notas de um aluno
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeByStudentDTO>> getGradesByStudentId(@PathVariable UUID studentId) {
        List<GradeByStudentDTO> grades = gradeService.getGradesByStudentId(studentId);
        if (grades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(grades);
    }

	@PostMapping
	public ResponseEntity<GradeDTO> createGrade(@RequestBody GradeDTO gradeDTO) {
		GradeDTO createdGrade = gradeService.createGrade(gradeDTO);
		return ResponseEntity.status(201).body(createdGrade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<GradeDTO> updateGrade(@PathVariable Long id, @RequestBody GradeDTO gradeDTO) {
		try {
			GradeDTO updatedGrade = gradeService.updateGrade(id, gradeDTO);
			return ResponseEntity.ok(updatedGrade);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
		try {
			gradeService.deleteGrade(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}

