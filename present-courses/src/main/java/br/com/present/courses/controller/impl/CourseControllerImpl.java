package br.com.present.courses.controller.impl;

import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.present.courses.controller.ICourseController;
import br.com.present.courses.model.CourseRequestDTO;
import br.com.present.courses.model.CourseResponseDTO;
import br.com.present.courses.service.CourseService;
import br.com.present.commons.util.consts.MicroservicesConsts;

@RestController
@AllArgsConstructor
@RequestMapping(MicroservicesConsts.ApiVersions.V1 + MicroservicesConsts.Course.ROUTE)
public final class CourseControllerImpl implements ICourseController {

    private final CourseService courseService;

	@Override
	@GetMapping("{id}")
    public ResponseEntity<CourseResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(courseService.findById(id));
    }
	
	@Override
	@PostMapping
    public ResponseEntity<CourseResponseDTO> create(@Valid @RequestBody CourseRequestDTO courseDto) {
		return new ResponseEntity<>(courseService.create(courseDto), HttpStatus.CREATED);
    }
	
	@Override
	@PutMapping("{id}")
    public ResponseEntity<CourseResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CourseRequestDTO courseDto) {
		return ResponseEntity.ok(courseService.update(id, courseDto));
    }
	
	@Override
	@DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		courseService.delete(id);
		return ResponseEntity.noContent().build();
    }
	
	@Override
	@GetMapping("/all")
    public ResponseEntity<List<CourseResponseDTO>> findAll() {
		return ResponseEntity.ok(courseService.findAll());
    }
}
