package com.ricajust.edugo.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
	private Long id;
	private Double value;
	private UUID studentId;
	private Long disciplineId;
}
