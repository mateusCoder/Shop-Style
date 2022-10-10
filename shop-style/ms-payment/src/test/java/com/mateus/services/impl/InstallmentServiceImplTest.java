package com.mateus.services.impl;

import com.mateus.builders.InstallmentBuilder;
import com.mateus.builders.PaymentBuilder;
import com.mateus.dtos.installment.InstallmentDto;
import com.mateus.entities.Installment;
import com.mateus.entities.Payment;
import com.mateus.repositories.InstallmentRepository;
import com.mateus.repositories.PaymentRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class InstallmentServiceImplTest {

    @InjectMocks
    InstallmentServiceImpl installmentService;

    @Mock
    InstallmentRepository installmentRepository;

    @Mock
    PaymentRepository paymentRepository;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSaveInstallmentDto() {
        MockHttpServletRequest request =new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(PaymentBuilder.getPayment()));
        when(installmentRepository.findById(anyLong())).thenReturn(Optional.of(InstallmentBuilder.getInstallment()));
        when(installmentRepository.save(any())).thenReturn(InstallmentBuilder.getInstallment());
        when(paymentRepository.save(any())).thenReturn(PaymentBuilder.getPayment());


        URI response = installmentService.save(InstallmentBuilder.getInstallmentFormDto());

        verify(installmentRepository, times(1)).save(any(Installment.class));
    }

    @Test
    void whenUpdateThenReturnUpdateProductDto() {
        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(PaymentBuilder.getPayment()));
        when(installmentRepository.findById(anyLong())).thenReturn(Optional.of(InstallmentBuilder.getInstallment()));
        when(installmentRepository.save(any())).thenReturn(InstallmentBuilder.getInstallment());
        when(paymentRepository.save(any())).thenReturn(PaymentBuilder.getPayment());

        InstallmentDto response = installmentService.update(InstallmentBuilder.getInstallment().getId(),
                InstallmentBuilder.getInstallmentFormDto());

        assertNotNull(response);
        assertEquals(InstallmentDto.class, response.getClass());
        assertEquals(InstallmentBuilder.getInstallment().getAmount(), response.getAmount());
    }

    @Test
    void whenDeleteByIdWithSuccess() {
        when(installmentRepository.findById(anyLong())).thenReturn(Optional.of(InstallmentBuilder.getInstallment()));
        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(PaymentBuilder.getPayment()));
        doNothing().when(installmentRepository).deleteById(anyLong());

        installmentService.delete(InstallmentBuilder.getInstallment().getId());

        verify(installmentRepository, times(1)).deleteById(InstallmentBuilder.getInstallment().getId());
    }

    @Test
    void whenFindOneThenReturnCheckPaymentExistence() {
        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(PaymentBuilder.getPayment()));

        Payment response = installmentService.checkPaymentExistence(PaymentBuilder.getPayment().getId());

        assertNotNull(response);
        assertEquals(Payment.class, response.getClass());
        assertEquals(PaymentBuilder.getPayment().getId(), response.getId());
    }

    @Test
    void whenFindOneThenReturnCheckInstallmentExistence() {
        when(installmentRepository.findById(anyLong())).thenReturn(Optional.of(InstallmentBuilder.getInstallment()));

        Installment response = installmentService.checkInstallmentExistence(InstallmentBuilder.getInstallment().getId());

        assertNotNull(response);
        assertEquals(Installment.class, response.getClass());
        assertEquals(InstallmentBuilder.getInstallment().getId(), response.getId());
    }
}