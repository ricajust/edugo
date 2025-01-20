package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.models.Student;
import com.ricajust.edugo.repositories.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository repository;

	public List<Student> getAllStudents(){
		return repository.findAll();
	}

	public Student getStudentById(UUID id){
		Optional<Student> student = repository.findById(id);
		return student.orElseThrow(() -> new RuntimeException("Estudante não localizado com o id: " + id));
	}

	public Student saveStudent(Student student) {
		return repository.save(student);
	}
}
