package com.maldo.backend.users.services.implementations;


import com.maldo.backend.users.domain.request.CreateUserRequest;
import com.maldo.backend.users.domain.response.UserResponse;
import com.maldo.backend.users.entity.Users;
import com.maldo.backend.users.mappers.UserMapper;
import com.maldo.backend.users.repositories.UsersRepository;
import com.maldo.backend.users.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface
{
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	public UserMapper uMap;


	@Override
	public List<Users> getAll() {
		return new ArrayList<>(usersRepository.findAll());
	}
	// Solo para procesos internos. Los usuarios se crean mediante DTO.
	@Override
	public UserResponse createUsers(CreateUserRequest request)
	{
		Users newUser = new Users();
		uMap.toEntityFromCreate(request, newUser);
		usersRepository.save(newUser);
		return uMap.toResponse(newUser);
	}
	@Override
	public void deleteUserById(Long id) {
		usersRepository.deleteById(id);
	}
}
