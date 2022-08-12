package com.mateus.services;

import com.mateus.dtos.CustomerDto;
import com.mateus.dtos.CustomerFormDto;
import com.mateus.dtos.CustomerFormDtoWithNoPassword;
import com.mateus.dtos.CustomerPasswordFormDto;

import java.net.URI;

public interface CustomerService {
    CustomerDto findById(Long id);

    URI save(CustomerFormDto customerForm);

    CustomerDto update(CustomerFormDtoWithNoPassword customForm, Long id);

    CustomerDto updatePassword(Long id, CustomerPasswordFormDto passwordFormDto);
}
