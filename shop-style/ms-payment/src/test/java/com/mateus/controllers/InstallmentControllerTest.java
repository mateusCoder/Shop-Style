package com.mateus.controllers;

import com.mateus.builders.InstallmentBuilder;
import com.mateus.dtos.installment.InstallmentDto;
import com.mateus.entities.Installment;
import com.mateus.services.impl.InstalmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
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
class InstallmentControllerTest {

    @InjectMocks
    InstallmentController installmentController;

    @Mock
    InstalmentServiceImpl instalmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSaveResponseEntityInstallmentDto() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/v1/installments/{id}")
                .build(InstallmentBuilder.getInstallment().getId());

        when(instalmentService.save(any())).thenReturn(uri);

        ResponseEntity<InstallmentDto> response = installmentController.save(InstallmentBuilder.getInstallmentFormDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/installments/1");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    void whenUpdateThenReturnUpdateResponseEntityInstallmentDto() {
        when(instalmentService.update(anyLong(), any())).thenReturn(InstallmentBuilder.getInstallmentDto());

        ResponseEntity<InstallmentDto> response = installmentController.update(
                InstallmentBuilder.getInstallment().getId(),
                InstallmentBuilder.getInstallmentFormDto());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenDeleteByIdWithSuccess(){
        doNothing().when(instalmentService).delete(anyLong());

        ResponseEntity<?> response = installmentController.delete(InstallmentBuilder.getInstallment().getId());

        assertEquals(ResponseEntity.class, response.getClass());
        verify(instalmentService, times(1)).delete(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}