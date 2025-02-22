package com.ricajust.edugo.dtos;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private UUID teacherId;
    private List<UUID> studentIds;
}
