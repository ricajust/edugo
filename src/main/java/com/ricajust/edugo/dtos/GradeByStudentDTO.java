package com.ricajust.edugo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeByStudentDTO {
	private Long id;
	private Double value;
	private DisciplineDTO discipline; // Informações da disciplina

	@Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DisciplineDTO {
        private Long id; // ID da disciplina
        private String name; // Nome da disciplina
    }
}
