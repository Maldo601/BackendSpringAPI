package com.maldo.backend.users.controllers;

import com.maldo.backend.users.entity.Users;
import com.maldo.backend.users.services.interfaces.UserServiceInterface;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/back/project/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController
{
	private final UserServiceInterface us;

	public UsersController(UserServiceInterface us) {this.us = us;}

	@GetMapping(value="/list")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Users> getAllUsers()
	{
		return us.getAll();
	}

	@DeleteMapping(value="/delete/{empId}")
	public void deleteEmployees(@PathVariable(value = "empId") Long id) {
		us.deleteUserById(id);
	}
}

