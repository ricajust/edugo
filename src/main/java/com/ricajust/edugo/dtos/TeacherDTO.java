package com.ricajust.edugo.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
	private UUID id;
	private String name;
	private String email;
	private Date hiringDate;
	private String specialization;
	private List<Long> disciplineIds;
}
