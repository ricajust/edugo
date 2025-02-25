package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
		return studentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<StudentDTO> getStudentById(UUID id) {
		return studentRepository.findById(id).map(this::convertToDTO);
	}

	public StudentDTO saveStudent(StudentDTO studentDTO) {
		Student student = convertToEntity(studentDTO);
		student = studentRepository.save(student);
		return convertToDTO(student);
	}

	public void deleteStudentById(UUID id) {
		studentRepository.deleteById(id);
	}

	private StudentDTO convertToDTO(Student student) {
		List<Long> disciplineIds = student.getStudentDisciplines().stream()
                .map(sd -> sd.getDiscipline().getId())
                .collect(Collectors.toList());
        return new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getEnrollmentDate(), disciplineIds);
	}

	private Student convertToEntity(StudentDTO studentDTO) {
		Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setEnrollmentDate(studentDTO.getEnrollmentDate());

        // Não manipula disciplinas diretamente aqui (relacionamento via StudentDiscipline)
        return student;
	}
}