package com.mateus.dtos;

import com.mateus.constants.State;
import com.mateus.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private State state;

    private String city;

    private String district;

    private String street;

    private String number;

    private String cep;

    private String complement;

    private Customer customer;
}
