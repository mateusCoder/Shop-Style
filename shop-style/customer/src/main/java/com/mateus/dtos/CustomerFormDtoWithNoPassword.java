package com.mateus.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateus.constants.Gender;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class CustomerFormDtoWithNoPassword {

    @CPF
    @NotBlank
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}")
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

    @NotNull
    private boolean active;
}
