package com.mateus.builder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateus.constants.Gender;
import com.mateus.dtos.CustomerDto;
import com.mateus.dtos.CustomerFormDto;
import com.mateus.dtos.CustomerFormDtoWithNoPassword;
import com.mateus.dtos.CustomerPasswordFormDto;
import com.mateus.entities.Address;
import com.mateus.entities.Customer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerBuilder {

    private static Long id = 1L;

    private static String cpf = "461.912.588-10";

    private static String firstName = "Mateus";

    private static String lastName = "Moraes";

    @Enumerated(EnumType.STRING)
    private static Gender gender = Gender.MASCULINO;

    private static LocalDate birthdate = LocalDate.of(2022, 8, 15);

    private static String email = "mateus@email.com";

    private static String password = "123";

    private static boolean active = true;

    private static List<Address> addresses = new ArrayList<>();

    public static Customer getCustomer(){
        return Customer.builder()
                .id(id)
                .cpf(cpf)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .password(password)
                .active(active)
                .addresses(addresses)
                .build();
    }

    public static CustomerDto getCustomerDto(){
        addresses.add(AddressBuilder.getAddress());
        return CustomerDto.builder()
                .cpf(cpf)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .active(active)
                .addresses(addresses)
                .build();
    }

    public static CustomerFormDto getCustomerFormDto(){
        return CustomerFormDto.builder()
                .cpf(cpf)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .active(active)
                .build();
    }

    public static CustomerFormDtoWithNoPassword getCustomerFormDtoWithNoPassword(){
        return CustomerFormDtoWithNoPassword.builder()
                .cpf(cpf)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .active(active)
                .build();
    }

    public static CustomerPasswordFormDto getPasswordFormDto(){
        return CustomerPasswordFormDto.builder()
                .password("senhanova")
                .build();
    }
}
