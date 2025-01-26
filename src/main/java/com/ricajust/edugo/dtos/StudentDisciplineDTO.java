package com.ricajust.edugo.dtos;

import com.ricajust.edugo.models.StudentDiscipline;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDisciplineDTO {
    private Long id;
    private Long disciplineId;
    private String disciplineName;
    private Integer absences;

    public StudentDisciplineDTO(StudentDiscipline studentDiscipline) {
        this.id = studentDiscipline.getId();
        this.disciplineId = studentDiscipline.getDiscipline().getId();
        this.disciplineName = studentDiscipline.getDiscipline().getName();
        this.absences = studentDiscipline.getAbsences();
    }

}

