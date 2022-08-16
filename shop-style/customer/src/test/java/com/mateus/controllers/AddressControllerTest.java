package com.mateus.controllers;

import com.mateus.builder.AddressBuilder;
import com.mateus.dtos.AddressDto;
import com.mateus.services.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AddressControllerTest {

    @InjectMocks
    AddressController addressController;

    @Mock
    AddressServiceImpl addressService;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/v1/address")
                .build(AddressBuilder.getAddress().getId());

        when(addressService.save(AddressBuilder.getAddressFormDto())).thenReturn(uri);

        ResponseEntity<AddressDto> response = addressController.save(AddressBuilder.getAddressFormDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/address");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void update() {
        when(addressService.update(anyLong(), any())).thenReturn(AddressBuilder.getAddressDto());

        ResponseEntity<AddressDto> response = addressController.update(id, AddressBuilder.getAddressFormDto());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void delete() {
        doNothing().when(addressService).delete(anyLong());

        ResponseEntity<?> response = addressController.delete(id);

        assertEquals(ResponseEntity.class, response.getClass());
        verify(addressService, times(1)).delete(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}