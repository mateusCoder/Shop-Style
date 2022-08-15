package com.mateus.services;

import com.mateus.builder.AddressBuilder;
import com.mateus.builder.CustomerBuilder;
import com.mateus.dtos.AddressDto;
import com.mateus.entities.Address;
import com.mateus.entities.Customer;
import com.mateus.repositories.AddressRepository;
import com.mateus.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class AddressServiceImplTest {

    @InjectMocks
    AddressServiceImpl addressService;

    @Mock
    AddressRepository addressRepository;

    @Mock
    CustomerRepository customerRepository;

    @Spy
    ModelMapper mapper;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerBuilder.getCustomer()));
        when(addressRepository.save(any())).thenReturn(AddressBuilder.getAddress());
        when(customerRepository.save(any())).thenReturn(CustomerBuilder.getCustomer());

        URI response = addressService.save(AddressBuilder.getAddressFormDto());

        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void update() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerBuilder.getCustomer()));
        when(addressRepository.save(any())).thenReturn(AddressBuilder.getAddress());
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(AddressBuilder.getAddress()));
        when(customerRepository.save(any())).thenReturn(CustomerBuilder.getCustomer());

        AddressDto response = addressService.update(id, AddressBuilder.getAddressFormDto());

        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void delete() {
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(AddressBuilder.getAddress()));
        doNothing().when(addressRepository).deleteById(anyLong());

        addressService.delete(id);

        verify(addressRepository, times(1)).deleteById(id);
    }

    @Test
    void checkExistence(){
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(AddressBuilder.getAddress()));

        Address response = addressService.checkExistence(id);

        assertNotNull(response);
        assertEquals(Address.class, response.getClass());
    }
}