package com.mateus.services;

import com.mateus.dtos.AddressDto;
import com.mateus.dtos.AddressFormDto;

import java.net.URI;

public interface AddressService {
    URI save(AddressFormDto addressFormDto);

    AddressDto update(Long id, AddressFormDto addressFormDto);

    void delete(Long id);
}
