package br.com.present.users.controller.impl;

import java.util.List;

import br.com.present.users.model.UserTypeResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.present.commons.util.consts.MicroservicesConsts;
import br.com.present.users.controller.IUserController;
import br.com.present.users.model.UserRequestDTO;
import br.com.present.users.model.UserResponseDTO;
import br.com.present.users.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(MicroservicesConsts.ApiVersions.V1 + MicroservicesConsts.User.ROUTE)
public final class UserControllerImpl implements IUserController {

    private final UserService userService;

    @Override
    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.findById(id));
    }
	
    @Override
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO userDto) {
		return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
    }
	
    @Override
    @PutMapping("{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userDto) {
		return ResponseEntity.ok(userService.update(id, userDto));
    }
	
    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
    }
    
    @Override
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
		return ResponseEntity.ok(userService.findAll());
    }
    
    @Override
    @GetMapping("/type/{id}")
    public ResponseEntity<UserTypeResponseDTO> findTypeByUserId(@PathVariable Long id) {
    	return ResponseEntity.ok(userService.findTypeByUserId(id));
    }
}
