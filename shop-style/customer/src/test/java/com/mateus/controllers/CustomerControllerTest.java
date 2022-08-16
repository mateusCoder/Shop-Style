package com.mateus.controllers;

import com.mateus.builder.CustomerBuilder;
import com.mateus.dtos.CustomerDto;
import com.mateus.services.CustomerServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerServiceImpl customerService;

    private final Long id = 1L;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/v1/customers")
                .build(CustomerBuilder.getCustomer().getId());

        when(customerService.save(any())).thenReturn(uri);

        ResponseEntity<CustomerDto> response = customerController.save(CustomerBuilder.getCustomerFormDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/customers");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void findById() {
        when(customerService.findById(anyLong())).thenReturn(CustomerBuilder.getCustomerDto());

        ResponseEntity<CustomerDto> response = customerController.findById(id);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void update() {
        when(customerService.update(any(), anyLong())).thenReturn(CustomerBuilder.getCustomerDto());

        ResponseEntity<CustomerDto> response = customerController.update(CustomerBuilder.getCustomerFormDtoWithNoPassword(), id);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updatePassword() {
        when(customerService.updatePassword(anyLong(), any())).thenReturn(CustomerBuilder.getCustomerDto());

        ResponseEntity<CustomerDto> response = customerController.updatePassword(id, CustomerBuilder.getPasswordFormDto());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}