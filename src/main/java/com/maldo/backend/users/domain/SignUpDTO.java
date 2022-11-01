package com.maldo.backend.users.domain;

import lombok.Data;


@Data
public class SignUpDTO
{
    private String name;
    private String username;
    private String lastname;
    private String secondLastName;
    private String email;
    private String pwd;
    private Boolean active;
}

