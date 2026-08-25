package com.estudo.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import com.estudo.api.dto.user.UserCreateDTO;
import com.estudo.api.dto.user.UserDataDTO;
import com.estudo.api.dto.user.UserUpdateDTO;
import com.estudo.api.error.user.UserAlreadyExistsException;
import com.estudo.api.error.user.UserCreateException;
import com.estudo.api.error.user.UserDeleteException;
import com.estudo.api.error.user.UserNotFoundException;
import com.estudo.api.error.user.UserUpdateException;
import com.estudo.api.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/users")
	public ResponseEntity<UserDataDTO> create(@Valid @RequestBody UserCreateDTO userCreateDTO) {
		try {
			UserDataDTO createdUser = userService.saveUser(userCreateDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (UserCreateException e) {
			return ResponseEntity.internalServerError().build();
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/users/{email}")
	public ResponseEntity<UserDataDTO> get(@PathVariable String email) {
		try {
			UserDataDTO user = userService.getUserByEmail(email);
			return ResponseEntity.ok(user);
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/users")
	public ResponseEntity<UserUpdateDTO> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
		try {
			UserUpdateDTO user = userService.updateUser(userUpdateDTO);
			return ResponseEntity.ok(user);
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (UserUpdateException e) {
			return ResponseEntity.internalServerError().build();
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/users/{uuid}")
	public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
		try {
			userService.deleteUser(uuid);
			return ResponseEntity.noContent().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (UserDeleteException e) {
			return ResponseEntity.internalServerError().build();
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
