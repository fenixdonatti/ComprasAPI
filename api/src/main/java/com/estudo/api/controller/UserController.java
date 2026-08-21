package com.estudo.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.api.dto.user.UserCreateDTO;
import com.estudo.api.dto.user.UserDataDTO;
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
		UserDataDTO createdUser = userService.saveUser(userCreateDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
}
