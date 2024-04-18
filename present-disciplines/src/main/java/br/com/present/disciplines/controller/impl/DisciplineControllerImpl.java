package br.com.present.disciplines.controller.impl;

import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.present.disciplines.controller.IDisciplineController;
import br.com.present.disciplines.model.DisciplineRequestDTO;
import br.com.present.disciplines.model.DisciplineResponseDTO;
import br.com.present.disciplines.service.DisciplineService;
import br.com.present.commons.util.consts.MicroservicesConsts;

@RestController
@AllArgsConstructor
@RequestMapping(MicroservicesConsts.ApiVersions.V1 + MicroservicesConsts.Discipline.ROUTE)
public final class DisciplineControllerImpl implements IDisciplineController {

    private final DisciplineService disciplineService;

	@Override
    @GetMapping("{id}")
    public ResponseEntity<DisciplineResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(disciplineService.findById(id));
    }

	@PostMapping
    @Override
    public ResponseEntity<DisciplineResponseDTO> create(@Valid @RequestBody DisciplineRequestDTO disciplineDto) {
		return new ResponseEntity<>(disciplineService.create(disciplineDto), HttpStatus.CREATED);
    }
	
	@PutMapping("{id}")
    @Override
    public ResponseEntity<DisciplineResponseDTO> update(@PathVariable Long id, @Valid @RequestBody DisciplineRequestDTO disciplineDto){
		return ResponseEntity.ok(disciplineService.update(id, disciplineDto));
    }
	
	@DeleteMapping("{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		disciplineService.delete(id);
		return ResponseEntity.noContent().build();
    }
	
	@GetMapping("/all")
    @Override
    public ResponseEntity<List<DisciplineResponseDTO>> findAll() {
		return ResponseEntity.ok(disciplineService.findAll());
    }
}
