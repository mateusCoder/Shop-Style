package com.mateus.dtos;

import com.mateus.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFormDto {

    @CPF
    @NotBlank
    private String cpf;

    @Size(max=100,min=3)
    @NotBlank
    private String firstName;

    @Size(max=100,min=3)
    @NotBlank
    private String lastName;

    @NotNull
    private Gender gender;

    @NotNull
    private LocalDate birthdate;

    @Email
    @NotBlank
    private String email;

    @Size(max=12,min=6)
    @NotBlank
    private String password;

    @NotNull
    private boolean active;
}
