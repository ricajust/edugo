package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.models.Teacher;
import com.ricajust.edugo.repositories.TeacherRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherService {

	@Autowired
	private TeacherRepository repository;

	public List<Teacher> getAllTeachers(){
		return repository.findAll();
	}

	public Teacher getTeacherById(UUID id) {
		Optional<Teacher> teacher = repository.findById(id);
		return teacher.orElseThrow(() -> new RuntimeException("Professor não localizado com o id: " + id));
	}

	public Teacher saveTeacher(Teacher teacher){
		return repository.save(teacher);
	}
}
