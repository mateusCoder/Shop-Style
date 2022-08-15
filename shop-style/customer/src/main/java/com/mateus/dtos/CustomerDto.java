package com.mateus.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateus.constants.Gender;
import com.mateus.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private String cpf;

    private String firstName;

    private String lastName;

    private Gender gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    private String email;

    private boolean active;

    private List<Address> addresses;
}
