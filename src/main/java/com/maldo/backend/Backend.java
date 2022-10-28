package com.maldo.backend;

import org.mapstruct.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperConfig
public class Backend
{
    public static void main(String[] args)
    {
        SpringApplication.run(Backend.class, args);
    }
}
