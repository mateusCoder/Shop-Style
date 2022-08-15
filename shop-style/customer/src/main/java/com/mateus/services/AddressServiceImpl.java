package com.mateus.services;

import com.mateus.dtos.AddressDto;
import com.mateus.dtos.AddressFormDto;
import com.mateus.entities.Address;
import com.mateus.entities.Customer;
import com.mateus.exceptions.ObjectNotFoundException;
import com.mateus.repositories.AddressRepository;
import com.mateus.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    private final ModelMapper mapper;

    @Override
    public URI save(AddressFormDto addressFormDto) {
        Customer customer = customerRepository.findById(addressFormDto.getCustomerId()).orElseThrow(() -> new ObjectNotFoundException("Address not found!"));
        Address address = mapper.map(addressFormDto, Address.class);
        address.setId(null);
        addressRepository.save(address);

        customer.setAddresses(address);
        customerRepository.save(customer);

        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(address.getId());
    }

    @Override
    public AddressDto update(Long id, AddressFormDto addressFormDto) {
        checkExistence(id);
        Customer customer = customerRepository.findById(addressFormDto.getCustomerId()).orElseThrow(() -> new ObjectNotFoundException("Address not found!"));
        Address address = mapper.map(addressFormDto, Address.class);
        address.setId(id);
        addressRepository.save(address);

        customer.setAddresses(address);
        customerRepository.save(customer);

        return mapper.map(address, AddressDto.class);
    }

    @Override
    public void delete(Long id) {
        checkExistence(id);
        addressRepository.deleteById(id);
    }

    public Address checkExistence(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Address not found!"));
    }
}
