package com.mateus.services;

import com.mateus.builder.CustomerBuilder;
import com.mateus.dtos.CustomerDto;
import com.mateus.entities.Customer;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Spy
    ModelMapper mapper;

    @Mock
    CustomerRepository customerRepository;

    private final Long id = 1L;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenfindByIdThenReturnCustomerDto() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerBuilder.getCustomer()));

        CustomerDto response = customerService.findById(id);

        assertNotNull(response);
        assertEquals(CustomerDto.class, response.getClass());
        assertEquals(CustomerBuilder.getCustomerDto().getCpf(), response.getCpf());
    }

    @Test
    void save() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(customerRepository.save(any())).thenReturn(CustomerBuilder.getCustomer());

        URI response = customerService.save(CustomerBuilder.getCustomerFormDto());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void update() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerBuilder.getCustomer()));
        when(customerRepository.save(any())).thenReturn(CustomerBuilder.getCustomer());

        CustomerDto response = customerService.update(CustomerBuilder.getCustomerFormDtoWithNoPassword(), id);

        assertNotNull(response);
        assertEquals(CustomerDto.class, response.getClass());
    }

    @Test
    void updatePassword() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerBuilder.getCustomer()));

        CustomerDto response = customerService.updatePassword(id, CustomerBuilder.getPasswordFormDto());

        assertNotNull(response);
        assertEquals(CustomerDto.class, response.getClass());
    }

    @Test
    void checkExistence(){
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerBuilder.getCustomer()));

        Customer response = customerService.checkExistence(id);

        assertNotNull(response);
        assertEquals(Customer.class, response.getClass());
    }
}