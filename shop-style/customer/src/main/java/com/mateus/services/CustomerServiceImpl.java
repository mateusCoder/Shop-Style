package com.mateus.services;

import com.mateus.dtos.CustomerDto;
import com.mateus.dtos.CustomerFormDto;
import com.mateus.dtos.CustomerFormDtoWithNoPassword;
import com.mateus.dtos.CustomerPasswordFormDto;
import com.mateus.entities.Address;
import com.mateus.entities.Customer;
import com.mateus.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    private final ModelMapper mapper;
    @Override
    public CustomerDto findById(Long id) {
        return mapper.map(checkExistence(id), CustomerDto.class);
    }

    @Override
    public URI save(CustomerFormDto customerForm) {
        Customer customer = mapper.map(customerForm, Customer.class);
        customerRepository.save(customer);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").build(customer.getId());
    }

    @Override
    public CustomerDto update(CustomerFormDtoWithNoPassword customerForm, Long id) {
        Customer customerPassword = checkExistence(id);
        String password = customerPassword.getPassword();
        List<Address> addresses = customerPassword.getAddresses();
        Customer customer = mapper.map(customerForm, Customer.class);
        customer.setId(id);
        customer.setPassword(password);
        addresses.forEach(customer::setAddresses);
        customerRepository.save(customer);
        return mapper.map(customer, CustomerDto.class);
    }

    @Override
    public CustomerDto updatePassword(Long id, CustomerPasswordFormDto passwordFormDto) {
        Customer customer = checkExistence(id);
        customer.setPassword(passwordFormDto.getPassword());
        return mapper.map(customer, CustomerDto.class);
    }

    private Customer checkExistence(Long id){
        return customerRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
