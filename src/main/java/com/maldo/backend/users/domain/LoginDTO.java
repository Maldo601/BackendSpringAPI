package com.maldo.backend.users.domain;

import lombok.Data;

@Data
public class LoginDTO
{
	private String email;
	private String pwd;
}
