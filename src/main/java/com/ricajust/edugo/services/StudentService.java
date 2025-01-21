package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.dtos.StudentDTO;
import com.ricajust.edugo.models.Student;
import com.ricajust.edugo.repositories.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

	@Autowired
	private final StudentRepository studentRepository;

	public List<StudentDTO> getAllStudents() {
		return studentRepository.findAll().stream().map(student -> new StudentDTO(
			student.getId(),
			student.getName(),
			student.getEmail(),
			student.getEnrollmentDate(),
			student.getGrades().stream().map(grade -> grade.getDiscipline().getId()).toList()
		)).toList();
	}

	public Optional<StudentDTO> getStudentById(UUID id) {
		return studentRepository.findById(id).map(student -> new StudentDTO(
			student.getId(),
			student.getName(),
			student.getEmail(),
			student.getEnrollmentDate(),
			student.getGrades().stream().map(grade -> grade.getDiscipline().getId()).toList()
		));
	}

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	public void deleteStudentById(UUID id) {
		studentRepository.deleteById(id);
	}
	
}
