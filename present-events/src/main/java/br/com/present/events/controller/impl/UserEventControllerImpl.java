package br.com.present.events.controller.impl;

import br.com.present.events.controller.IUserEventController;
import br.com.present.events.model.UserEventRequestDTO;
import br.com.present.events.model.UserEventResponseDTO;
import br.com.present.events.service.UserEventService;
import br.com.present.commons.util.consts.MicroservicesConsts;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(MicroservicesConsts.ApiVersions.V1 + MicroservicesConsts.Event.ROUTE + "/user")
public final class UserEventControllerImpl implements IUserEventController {

    private final UserEventService userEventService;

	@Override
	@GetMapping("{id}")
    public ResponseEntity<UserEventResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(userEventService.findById(id));
    }
	
	@Override
	@PostMapping
    public ResponseEntity<UserEventResponseDTO> create(@Valid @RequestBody UserEventRequestDTO userEventDto) {
		return new ResponseEntity<>(userEventService.create(userEventDto), HttpStatus.CREATED);
    }
	
	@Override
	@PutMapping("{id}")
    public ResponseEntity<UserEventResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserEventRequestDTO userEventDto) {
		return ResponseEntity.ok(userEventService.update(id, userEventDto));
    }
	
	@Override
	@DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		userEventService.delete(id);
		return ResponseEntity.noContent().build();
    }
	
	@Override
	@GetMapping("/all")
    public ResponseEntity<List<UserEventResponseDTO>> findAll() {
		return ResponseEntity.ok(userEventService.findAll());
    }
	
}
