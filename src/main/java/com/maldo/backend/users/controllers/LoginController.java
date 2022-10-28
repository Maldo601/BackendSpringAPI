package com.maldo.backend.users.controllers;

import com.maldo.backend.users.services.interfaces.UserDtoService;
import com.maldo.backend.users.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.maldo.backend.users.domain.SignUpDTO;
import com.maldo.backend.users.domain.LoginDTO;
import org.springframework.http.ResponseEntity;
import com.maldo.backend.users.enums.Messages;
import com.maldo.backend.users.entity.Users;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController
{
	private final UsersRepository userRepository;
	private final UserDtoService dtoService;

	@Autowired
	public LoginController(UserDtoService dtoService, UsersRepository userRepository) {
		this.dtoService = dtoService;
		this.userRepository = userRepository;
	}

	@PostMapping("/signin")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto) {
		Optional<Users> optUsr = userRepository.findByEmail(loginDto.getEmail());
		if(optUsr.isPresent())
		{
			 Users user = optUsr.get();
			 if (user.getPwd().equals(loginDto.getPwd()))
				 return
						 new ResponseEntity<>(Messages.CORRECT_LOGIN.getMsg(), HttpStatus.OK);
		}
		return
				new ResponseEntity<>(Messages.BAD_LOGIN.getMsg(), HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/signup")
	public ResponseEntity< ? > registerUser(@RequestBody SignUpDTO dataTransfer)
	{
		if(Boolean.TRUE.equals(userRepository.existsByUsername(dataTransfer.getUsername())))
			return
					new ResponseEntity<>(Messages.USERNAME_EXIST.getMsg(), HttpStatus.BAD_REQUEST);

		if(Boolean.TRUE.equals(userRepository.existsByEmail(dataTransfer.getEmail())))
			return
					new ResponseEntity<>(Messages.EMAIL_EXIST.getMsg(), HttpStatus.BAD_REQUEST);

		dtoService.createUser(dataTransfer);

		return
				new ResponseEntity<>(Messages.REGISTER_SUCCES.getMsg(), HttpStatus.OK);
	}
}