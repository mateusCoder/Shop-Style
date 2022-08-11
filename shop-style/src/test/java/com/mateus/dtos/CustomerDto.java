package com.mateus.dtos;

import com.mateus.constants.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String cpf;

    private String firstName;

    private String lastName;

    private Sex sex;

    private LocalDate birthdate;

    private String email;

    private String password;

    private boolean active;

}
