package br.com.present.classes.controller.impl;

import br.com.present.classes.controller.IClassCourseController;
import br.com.present.classes.model.ClassCourseRequestDTO;
import br.com.present.classes.model.ClassCourseResponseDTO;
import br.com.present.classes.service.ClassCourseService;
import br.com.present.commons.util.consts.MicroservicesConsts;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(MicroservicesConsts.ApiVersions.V1 + MicroservicesConsts.Class.ROUTE + "/course")
public final class ClassCourseControllerImpl implements IClassCourseController {

    private final ClassCourseService classCourseService;

	@Override
	@GetMapping("{id}")
    public ResponseEntity<ClassCourseResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(classCourseService.findById(id));
    }
	
	@Override
	@PostMapping
    public ResponseEntity<ClassCourseResponseDTO> create(@Valid @RequestBody ClassCourseRequestDTO classCourseDto) {
		return new ResponseEntity<>(classCourseService.create(classCourseDto), HttpStatus.CREATED);
    }
	
	@Override
	@PutMapping("{id}")
    public ResponseEntity<ClassCourseResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClassCourseRequestDTO classCourseDto) {
		return ResponseEntity.ok(classCourseService.update(id, classCourseDto));
    }
	
	@Override
	@DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		classCourseService.delete(id);
		return ResponseEntity.noContent().build();
    }
	
	@Override
	@GetMapping("/all")
    public ResponseEntity<List<ClassCourseResponseDTO>> findAll() {
		return ResponseEntity.ok(classCourseService.findAll());
    }
	
}
