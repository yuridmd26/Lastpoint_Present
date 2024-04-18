package br.com.present.classes.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.present.classes.controller.IClassController;
import br.com.present.classes.model.ClassRequestDTO;
import br.com.present.classes.model.ClassResponseDTO;
import br.com.present.classes.model.ClassesNowAndFutureResponseDTO;
import br.com.present.classes.service.ClassService;
import br.com.present.commons.util.consts.MicroservicesConsts;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(MicroservicesConsts.ApiVersions.V1 + MicroservicesConsts.Class.ROUTE)
public final class ClassControllerImpl implements IClassController{

    private final ClassService classService;

	@Override
	@GetMapping("{id}")
    public ResponseEntity<ClassResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(classService.findById(id));
    }
	
	@Override
	@PostMapping
    public ResponseEntity<ClassResponseDTO> create(@Valid @RequestBody ClassRequestDTO classDto) {
		return new ResponseEntity<>(classService.create(classDto), HttpStatus.CREATED);
    }
	
	@Override
	@PutMapping("{id}")
    public ResponseEntity<ClassResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClassRequestDTO classDto) {
		return ResponseEntity.ok(classService.update(id, classDto));
    }
	
	@Override
	@DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		classService.delete(id);
		return ResponseEntity.noContent().build();
    }
	
	@Override
	@GetMapping("/all")
    public ResponseEntity<List<ClassResponseDTO>> findAll() {
		return ResponseEntity.ok(classService.findAll());
    }
	
	@Override
    @GetMapping("/user")
    public ResponseEntity<List<ClassesNowAndFutureResponseDTO>> findClassesUserForTypeByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(classService.findClassesUserForTypeByUserId(userId));
    }
	
}
