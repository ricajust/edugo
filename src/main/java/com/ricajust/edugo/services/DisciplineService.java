package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.models.Discipline;
import com.ricajust.edugo.models.Teacher;
import com.ricajust.edugo.repositories.DisciplineRepository;
import com.ricajust.edugo.repositories.TeacherRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DisciplineService {

	@Autowired
	private DisciplineRepository disciplineRepository;
	@Autowired
	private TeacherRepository teacherRepository;

	public List<Discipline> getAllDisciplines(){
		return disciplineRepository.findAll();
	}

	public Discipline getDisciplineByid(Long id){
		Optional<Discipline> discipline = disciplineRepository.findById(id);
		return discipline.orElseThrow(() -> new RuntimeException("Disciplina não localizada com o id: " + id));
	}

	public Discipline saveDiscipline(Discipline discipline) {
		if (discipline.getTeacher() != null) {
			UUID teacherId = discipline.getTeacher().getId();
			Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalArgumentException("Professor não localizado com o id: " + teacherId));
			discipline.setTeacher(teacher); // Associate the existing teacher
		}
		return disciplineRepository.save(discipline);
	}
}
