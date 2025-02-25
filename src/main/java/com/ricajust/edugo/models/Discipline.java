package com.ricajust.edugo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discipline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	// @ManyToMany(mappedBy = "disciplines")
	// private List<Student> students = new ArrayList<>();

	@OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore // Evita serializar `StudentDiscipline` recursivamente
	private List<StudentDiscipline> studentDisciplines = new ArrayList<>();
}
