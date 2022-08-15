package com.mateus.dtos;

import com.mateus.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerFormDto {

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

    @Size(max=12,min=6)
    @NotBlank
    private String password;

    @NotNull
    private boolean active;
}
