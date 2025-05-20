package com.askmentor.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.askmentor.model.User;
import com.askmentor.service.UserService;
import com.askmentor.dto.UserUpdateRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{user_id}")
	public ResponseEntity<User> getUser(@PathVariable int user_id) {
		User user = userService.getUser(user_id);
		return ResponseEntity.ok(user);
	}

	@PatchMapping("/{user_id}")
	public ResponseEntity<String> updateUser(@PathVariable int user_id, @RequestBody UserUpdateRequest request) {
		String result = userService.updateUser(user_id, request);
		return ResponseEntity.ok(result);
	}
}