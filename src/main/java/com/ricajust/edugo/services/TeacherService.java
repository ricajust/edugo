package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.dtos.TeacherDTO;
import com.ricajust.edugo.models.Discipline;
import com.ricajust.edugo.models.Teacher;
import com.ricajust.edugo.repositories.TeacherRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherService {

	@Autowired
	private final TeacherRepository teacherRepository;

	public List<TeacherDTO> getAllTeachers() {
		return teacherRepository.findAll().stream().map(teacher -> new TeacherDTO(
			teacher.getId(),
			teacher.getName(),
			teacher.getEmail(),
			teacher.getHiringDate(),
			teacher.getDisciplines().stream().map(Discipline::getId).toList()
		)).toList();
	}

	public Optional<TeacherDTO> getTeacherById(UUID id) {
		return teacherRepository.findById(id).map(teacher -> new TeacherDTO(
			teacher.getId(),
			teacher.getName(),
			teacher.getEmail(),
			teacher.getHiringDate(),
			teacher.getDisciplines().stream().map(Discipline::getId).toList()
		));
	}

	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public void deleteTeacherById(UUID id) {
		teacherRepository.deleteById(id);
	}
}
