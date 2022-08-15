package com.mateus.builder;

import com.mateus.constants.State;
import com.mateus.dtos.AddressDto;
import com.mateus.dtos.AddressFormDto;
import com.mateus.entities.Address;
import com.mateus.entities.Customer;

public class AddressBuilder {

    private static Long id = 1L;

    private static State state = State.SAO_PAULO;

    private static String city = "Engenheiro Coelho";

    private static String district = "Campinas e região";

    private static String street = "Rua Minas Gerais";

    private static String number = "2A";

    private static String cep = "13455-100";

    private static String complement = "perto do mercado A";

    private static Customer customer = CustomerBuilder.getCustomer();

    public static Address getAddress(){
        return Address.builder()
                .id(id)
                .city(city)
                .district(district)
                .street(street)
                .number(number)
                .cep(cep)
                .complement(complement)
                .build();
    }

    public static AddressDto getAddressDto(){
        return AddressDto.builder()
                .state(state)
                .city(city)
                .district(district)
                .street(street)
                .number(number)
                .cep(cep)
                .complement(complement)
                .customer(customer)
                .build();
    }

    public static AddressFormDto getAddressFormDto(){
        return AddressFormDto.builder()
                .state(state)
                .city(city)
                .district(district)
                .street(street)
                .number(number)
                .cep(cep)
                .complement(complement)
                .customerId(id)
                .build();
    }
}
