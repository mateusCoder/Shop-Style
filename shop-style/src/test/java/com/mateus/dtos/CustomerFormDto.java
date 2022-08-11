package com.mateus.dtos;

import com.mateus.constants.Sex;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerFormDto {

    private String cpf;

    private String firstName;

    private String lastName;

    private Sex sex;

    private LocalDate birthdate;

    private String email;

    private String password;

    private boolean active;

}
