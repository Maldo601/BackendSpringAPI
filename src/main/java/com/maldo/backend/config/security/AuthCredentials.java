package com.maldo.backend.config.security;

import lombok.Data;

@Data
public class AuthCredentials
{
    private String email;
    private String pwd;
}
