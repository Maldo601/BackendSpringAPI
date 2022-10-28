package com.maldo.backend.users.services.interfaces;

import com.maldo.backend.users.domain.request.CreateUserRequest;
import com.maldo.backend.users.domain.response.UserResponse;
import com.maldo.backend.users.entity.Users;

import java.util.List;

public interface UserServiceInterface
{
	List<Users> getAll();
	UserResponse createUsers(CreateUserRequest signUpDto);
	void deleteUserById(Long id);
}
