package com.maldo.backend.users.mappers;

import com.maldo.backend.config.constants.MapperConstants;
import com.maldo.backend.users.domain.request.CreateUserRequest;
import com.maldo.backend.users.domain.response.UserResponse;
import com.maldo.backend.users.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MapperConstants.COMPONENT_MODEL, uses = {Users.class})
public interface UserMapper
{
	static UserMapper getMapper() {
		return Mappers.getMapper(UserMapper.class);
	}
	void toEntityFromCreate(CreateUserRequest createOutRequest, @MappingTarget Users user);
	UserResponse toResponse(Users user);
	CreateUserRequest toRequest(UserResponse response);
	List<UserResponse> toResponseList(List<Users> user);
}
