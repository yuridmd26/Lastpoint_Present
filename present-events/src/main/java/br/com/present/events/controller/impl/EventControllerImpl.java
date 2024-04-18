package br.com.present.events.controller.impl;

import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.present.events.controller.IEventController;
import br.com.present.events.model.EventRequestDTO;
import br.com.present.events.model.EventResponseDTO;
import br.com.present.events.service.EventService;
import br.com.present.commons.util.consts.MicroservicesConsts;

@RestController
@AllArgsConstructor
@RequestMapping(MicroservicesConsts.ApiVersions.V1 + MicroservicesConsts.Event.ROUTE)
public final class EventControllerImpl implements IEventController {

    private final EventService eventService;

	@Override
    @GetMapping("{id}")
    public ResponseEntity<EventResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(eventService.findById(id));
    }

	@PostMapping
    @Override
    public ResponseEntity<EventResponseDTO> create(@Valid @RequestBody EventRequestDTO eventDto) {
		return new ResponseEntity<>(eventService.create(eventDto), HttpStatus.CREATED);
    }
	
	@PutMapping("{id}")
    @Override
    public ResponseEntity<EventResponseDTO> update(@PathVariable Long id,  @Valid @RequestBody EventRequestDTO eventDto) {
		return ResponseEntity.ok(eventService.update(id, eventDto));
    }
	
	@DeleteMapping("{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		eventService.delete(id);
		return ResponseEntity.noContent().build();
    }
	
	@GetMapping("/all")
    @Override
    public ResponseEntity<List<EventResponseDTO>> findAll() {
		return ResponseEntity.ok(eventService.findAll());
    }
}