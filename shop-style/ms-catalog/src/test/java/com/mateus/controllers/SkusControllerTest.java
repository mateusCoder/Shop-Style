package com.mateus.controllers;

import com.mateus.builders.ProductBuilder;
import com.mateus.builders.SkuBuilder;
import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.sku.SkuDto;
import com.mateus.services.impl.SkusServiceImpl;
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
import static org.mockito.Mockito.times;

@AutoConfigureTestDatabase
class SkusControllerTest {

    @InjectMocks
    SkusController skusController;

    @Mock
    SkusServiceImpl skusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSaveResponseEntitySkuDto() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/v1/skus/{id}")
                .build(SkuBuilder.getSku().getId());

        when(skusService.save(any())).thenReturn(uri);

        ResponseEntity<URI> response = skusController.save(SkuBuilder.getSkuDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/skus/1");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void whenUpdateThenReturnUpdateResponseEntitySkuDto() {
        when(skusService.update(anyLong(), any())).thenReturn(SkuBuilder.getSkuDto());

        ResponseEntity<SkuDto> response = skusController.update(SkuBuilder.getSku().getId(),
                SkuBuilder.getSkuDto());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenDeleteByIdWithSuccess(){
        doNothing().when(skusService).delete(anyLong());

        ResponseEntity<?> response = skusController.delete(SkuBuilder.getSku().getId());

        assertEquals(ResponseEntity.class, response.getClass());
        verify(skusService, times(1)).delete(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}