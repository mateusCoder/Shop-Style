package com.mateus.dtos;

import com.mateus.entities.Customer;
import lombok.Data;

@Data
public class AddressFormDto {

    private String state;

    private String city;

    private String district;

    private String street;

    private String number;

    private String cep;

    private String complement;

    private Customer customer;
}
