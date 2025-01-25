package com.ricajust.edugo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double value;

	@ManyToOne
	@JoinColumn(name = "student_id")
	@JsonIgnore // Evitar serialização do estudante dentro de Grade
	private Student student;

	@ManyToOne
	@JoinColumn(name = "discipline_id")
	private Discipline discipline;

}
