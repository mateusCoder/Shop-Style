package com.mateus.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateus.constants.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoWithPassword {

    private String cpf;

    private String firstName;

    private String lastName;

    private Sex sex;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    private String email;

    private String password;

    private boolean active;

}
