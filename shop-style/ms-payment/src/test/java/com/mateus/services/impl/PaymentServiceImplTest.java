package com.mateus.services.impl;

import com.mateus.builders.PaymentBuilder;
import com.mateus.dtos.payment.PaymentDto;
import com.mateus.entities.Payment;
import com.mateus.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSavePaymentDto() {
        MockHttpServletRequest request =new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(PaymentBuilder.getPayment()));
        when(paymentRepository.save(any())).thenReturn(PaymentBuilder.getPayment());

        URI response = paymentService.save(PaymentBuilder.getPaymentFormDto());

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void whenFindAllThenReturnPageableProductDto() {
        when(paymentRepository.findAll((Pageable) any())).thenReturn(PaymentBuilder.getPaymentPageable());

        Pageable page = PageRequest.of(0, 100);
        Page<PaymentDto> response = paymentService.findAll(page);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
    }

    @Test
    void whenUpdateThenReturnUpdatePaymentDto() {
        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(PaymentBuilder.getPayment()));
        when(paymentRepository.save(any())).thenReturn(PaymentBuilder.getPayment());

        PaymentDto response = paymentService.update(PaymentBuilder.getPayment().getId(),
                PaymentBuilder.getPaymentFormDto());

        assertNotNull(response);
        assertEquals(PaymentDto.class, response.getClass());
        assertEquals(PaymentBuilder.getPayment().getType(), response.getType());
    }

    @Test
    void whenDeleteByIdWithSuccess() {
        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(PaymentBuilder.getPayment()));
        doNothing().when(paymentRepository).deleteById(anyLong());

        paymentService.delete(PaymentBuilder.getPayment().getId());

        verify(paymentRepository, times(1)).deleteById(PaymentBuilder.getPayment().getId());
    }

    @Test
    void whenFindOneThenReturnCheckPaymentExistence() {
        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(PaymentBuilder.getPayment()));

        Payment response = paymentService.checkPaymentExistence(PaymentBuilder.getPayment().getId());

        assertNotNull(response);
        assertEquals(Payment.class, response.getClass());
        assertEquals(PaymentBuilder.getPayment().getId(), response.getId());
    }
}