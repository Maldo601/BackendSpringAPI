package com.maldo.backend.users.controllers;

import com.maldo.backend.users.entity.Users;
import com.maldo.backend.users.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UsersController
{
	private final UsersRepository usersRepository;

	@GetMapping(value = "/list")
	public List<Users> listUsers() { return usersRepository.findAll(); }

}

