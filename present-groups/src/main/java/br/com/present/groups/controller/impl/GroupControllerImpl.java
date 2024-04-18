package br.com.present.groups.controller.impl;

import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.present.groups.controller.IGroupController;
import br.com.present.groups.model.GroupRequestDTO;
import br.com.present.groups.model.GroupResponseDTO;
import br.com.present.groups.service.GroupService;
import br.com.present.commons.util.consts.MicroservicesConsts;

@RestController
@AllArgsConstructor
@RequestMapping(MicroservicesConsts.ApiVersions.V1 + MicroservicesConsts.Group.ROUTE)
public final class GroupControllerImpl implements IGroupController {

    private final GroupService groupService;

    @Override
    @GetMapping("{id}")
    public ResponseEntity<GroupResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(groupService.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<GroupResponseDTO> create(@Valid @RequestBody GroupRequestDTO groupDto) {
		return new ResponseEntity<>(groupService.create(groupDto), HttpStatus.CREATED);
    }
	
    @Override
    @PutMapping("{id}")
    public ResponseEntity<GroupResponseDTO> update(@PathVariable Long id, @Valid @RequestBody GroupRequestDTO groupDto) {
		return ResponseEntity.ok(groupService.update(id, groupDto));
    }
	
    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		groupService.delete(id);
		return ResponseEntity.noContent().build();
    }
	
	@Override
    @GetMapping("/all")
    public ResponseEntity<List<GroupResponseDTO>> findAll() {
		return ResponseEntity.ok(groupService.findAll());
    }
}
