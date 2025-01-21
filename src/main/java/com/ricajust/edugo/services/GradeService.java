package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.dtos.GradeDTO;
import com.ricajust.edugo.models.Discipline;
import com.ricajust.edugo.models.Grade;
import com.ricajust.edugo.models.Student;
import com.ricajust.edugo.repositories.DisciplineRepository;
import com.ricajust.edugo.repositories.GradeRepository;
import com.ricajust.edugo.repositories.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GradeService {
	@Autowired
	private final GradeRepository gradeRepository;
	@Autowired
	private final StudentRepository studentRepository;
	@Autowired
	private final DisciplineRepository disciplineRepository;

	public List<GradeDTO> getAllGrades() {
		return gradeRepository.findAll().stream().map(grade -> new GradeDTO(
			grade.getId(),
			grade.getValue(),
			grade.getStudent().getId(),
			grade.getDiscipline().getId()
		)).toList();
	}

	public Optional<GradeDTO> getGradeById(Long id) {
		return gradeRepository.findById(id).map(grade -> new GradeDTO(
			grade.getId(),
			grade.getValue(),
			grade.getStudent().getId(),
			grade.getDiscipline().getId()
		));
	}

	public Grade saveGrade(Grade grade) {
		return gradeRepository.save(grade);
	}

	public GradeDTO createGrade(GradeDTO gradeDTO) {
		// Validate Student
		Student student = studentRepository.findById(gradeDTO.getStudentId())
			.orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + gradeDTO.getStudentId()));

		// Validate Discipline
		Discipline discipline = disciplineRepository.findById(gradeDTO.getDisciplineId())
			.orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + gradeDTO.getDisciplineId()));

		// Create and Save Grade
		Grade grade = new Grade();
		grade.setValue(gradeDTO.getValue());
		grade.setStudent(student);
		grade.setDiscipline(discipline);

		Grade savedGrade = gradeRepository.save(grade);

		return new GradeDTO(
			savedGrade.getId(),
			savedGrade.getValue(),
			savedGrade.getStudent().getId(),
			savedGrade.getDiscipline().getId()
		);
	}

	public void deleteGrade(Long id) {
		if (!gradeRepository.existsById(id)) {
			throw new IllegalArgumentException("Grade not found with ID: " + id);
		}
		gradeRepository.deleteById(id);
	}

	public GradeDTO updateGrade(Long id, GradeDTO gradeDTO) {
		Grade grade = gradeRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Grade not found with ID: " + id));

		// Update values
		grade.setValue(gradeDTO.getValue());

		// Validate Student
		Student student = studentRepository.findById(gradeDTO.getStudentId())
			.orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + gradeDTO.getStudentId()));
		grade.setStudent(student);

		// Validate Discipline
		Discipline discipline = disciplineRepository.findById(gradeDTO.getDisciplineId())
			.orElseThrow(() -> new IllegalArgumentException("Discipline not found with ID: " + gradeDTO.getDisciplineId()));
		grade.setDiscipline(discipline);

		// Save updated grade
		Grade updatedGrade = gradeRepository.save(grade);

		return new GradeDTO(
			updatedGrade.getId(),
			updatedGrade.getValue(),
			updatedGrade.getStudent().getId(),
			updatedGrade.getDiscipline().getId()
		);
	}
}
