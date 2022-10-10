package com.mateus.controllers;

import com.mateus.builders.PaymentBuilder;
import com.mateus.dtos.payment.PaymentDto;
import com.mateus.services.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class PaymentControllerTest {

    @InjectMocks
    PaymentController paymentController;

    @Mock
    PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSaveResponseEntityPaymentDto() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/v1/payments/{id}")
                .build(PaymentBuilder.getPayment().getId());

        when(paymentService.save(any())).thenReturn(uri);

        ResponseEntity<PaymentDto> response = paymentController.save(PaymentBuilder.getPaymentFormDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/payments/1");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void findAll() {
    }

    @Test
    void whenUpdateThenReturnUpdateResponseEntityPaymentDto() {
        when(paymentService.update(anyLong(), any())).thenReturn(PaymentBuilder.getPaymentDto());

        ResponseEntity<PaymentDto> response = paymentController.update(PaymentBuilder.getPayment().getId(),
                PaymentBuilder.getPaymentFormDto());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenDeleteByIdWithSuccess(){
        doNothing().when(paymentService).delete(anyLong());

        ResponseEntity<?> response = paymentController.delete(PaymentBuilder.getPayment().getId());

        assertEquals(ResponseEntity.class, response.getClass());
        verify(paymentService, times(1)).delete(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}