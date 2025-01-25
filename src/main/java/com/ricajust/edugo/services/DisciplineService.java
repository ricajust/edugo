package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.dtos.DisciplineDTO;
import com.ricajust.edugo.models.Discipline;
import com.ricajust.edugo.models.Student;
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
	private final DisciplineRepository disciplineRepository;
	@Autowired
	private final TeacherRepository teacherRepository;

	public List<DisciplineDTO> getAllDisciplines() {
		return disciplineRepository.findAll().stream()
		.map(discipline -> new DisciplineDTO(
			discipline.getId(),
			discipline.getName(),
			discipline.getDescription(),
			discipline.getPrice(),
			discipline.getTeacher().getId(),
			discipline.getStudents().stream().map(Student::getId).toList()
		)).toList();
	}

	public List<DisciplineDTO> getAllDisciplinesById(List<Long> disciplineIds) {
		return disciplineRepository.findAllById(disciplineIds).stream().map(discipline -> new DisciplineDTO(
			discipline.getId(),
			discipline.getName(),
			discipline.getDescription(),
			discipline.getPrice(),
			discipline.getTeacher().getId(),
			discipline.getStudents().stream().map(Student::getId).toList()
		)).toList();
    }

	public Optional<DisciplineDTO> getDisciplineById(Long id) {
		return disciplineRepository.findById(id).map(discipline -> new DisciplineDTO(
			discipline.getId(),
			discipline.getName(),
			discipline.getDescription(),
			discipline.getPrice(),
			discipline.getTeacher().getId(),
			discipline.getStudents().stream().map(Student::getId).toList()
		));
	}

	public Discipline saveDiscipline(Discipline discipline) {
		return disciplineRepository.save(discipline);
	}

	public DisciplineDTO createDiscipline(DisciplineDTO disciplineDTO) {
		// Validar se o professor existe
		Teacher teacher = teacherRepository.findById(disciplineDTO.getTeacherId())
			.orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + disciplineDTO.getTeacherId()));

		// Criar a disciplina
		Discipline discipline = new Discipline();
		discipline.setName(disciplineDTO.getName());
		discipline.setDescription(disciplineDTO.getDescription());
		discipline.setPrice(disciplineDTO.getPrice());
		discipline.setTeacher(teacher);

		Discipline savedDiscipline = disciplineRepository.save(discipline);

		return new DisciplineDTO(
			savedDiscipline.getId(),
			savedDiscipline.getName(),
			savedDiscipline.getDescription(),
			savedDiscipline.getPrice(),
			savedDiscipline.getTeacher().getId(),
			savedDiscipline.getStudents().stream().map(Student::getId).toList()
		);
	}

	public void deleteDisciplineById(Long id) {
		disciplineRepository.deleteById(id);
	}
}