package com.maldo.backend.users.controllers;

import com.maldo.backend.users.domain.LoginDTO;
import com.maldo.backend.users.domain.SignUpDTO;
import com.maldo.backend.users.entity.Users;
import com.maldo.backend.users.enums.Messages;
import com.maldo.backend.users.repositories.UsersRepository;
import com.maldo.backend.users.services.interfaces.UserDtoService;
import com.maldo.backend.users.services.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Para entrar en la API : localhost:8080/home/login
 * --------------------------------------------------
 * Las credenciales por mas que sean correctas solo seran efectivas
 * en esa ruta. Pendiente de optimizar.
 */
@RestController
@RequestMapping(value = "/home")
@AllArgsConstructor
public class UsersController
{
	private final UsersRepository userRepository;
	private final UserDtoService dtoService;

	@Autowired
	public UsersController(UserDtoService dtoService, UsersRepository userRepository) {
		this.dtoService = dtoService;
		this.userRepository = userRepository;
	}

	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto)
	{
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
	public ResponseEntity<?> registerUser(@RequestBody SignUpDTO dataTransfer)
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
	@GetMapping(value = "/list")
	public List<Users> listUsers() { return userRepository.findAll(); }

}

