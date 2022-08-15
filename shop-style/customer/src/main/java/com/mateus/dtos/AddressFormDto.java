package com.mateus.dtos;

import com.mateus.constants.State;
import com.mateus.entities.Customer;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddressFormDto {


    private State state;

    @NotBlank
    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String cep;

    private String complement;

    private Long customerId;
}
