package com.ricajust.edugo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricajust.edugo.dtos.DisciplineDTO;
import com.ricajust.edugo.models.Discipline;
import com.ricajust.edugo.services.DisciplineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    @Autowired
    private final DisciplineService disciplineService;

    @GetMapping
    public ResponseEntity<List<DisciplineDTO>> getAllDisciplines() {
        List<DisciplineDTO> disciplines = disciplineService.getAllDisciplines();
        return ResponseEntity.ok(disciplines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDTO> getDisciplineById(@PathVariable Long id) {
        return disciplineService.getDisciplineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DisciplineDTO> createDiscipline(@RequestBody DisciplineDTO disciplineDTO) {
        DisciplineDTO savedDiscipline = disciplineService.createDiscipline(disciplineDTO);
        return ResponseEntity.status(201).body(savedDiscipline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody Discipline updatedDiscipline) {
        return disciplineService.getDisciplineById(id).map(existingDiscipline -> {
            updatedDiscipline.setId(existingDiscipline.getId());
            Discipline savedDiscipline = disciplineService.saveDiscipline(updatedDiscipline);
            return ResponseEntity.ok(savedDiscipline);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        if (disciplineService.getDisciplineById(id).isPresent()) {
            disciplineService.deleteDisciplineById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
