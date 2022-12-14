package com.mateus.dtos;

import com.mateus.constants.State;
import com.mateus.entities.Customer;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddressFormDto {

    @NotNull
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
