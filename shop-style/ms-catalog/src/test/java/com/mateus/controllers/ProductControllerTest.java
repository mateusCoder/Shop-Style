package com.mateus.controllers;

import com.mateus.builders.ProductBuilder;
import com.mateus.dtos.product.ProductDto;
import com.mateus.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindAllThenReturnResponseEntityPageableProductDto() {
        when(productService.findAll((Pageable) any())).thenReturn(ProductBuilder.getProductDtoPageable());

        Pageable page = PageRequest.of(0, 100);
        ResponseEntity<Page<ProductDto>> response = productController.findAll(page);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenFindOneThenReturnResponseEntityProductDto() {
        when(productService.findById(anyLong())).thenReturn(ProductBuilder.getProductDto());

        ResponseEntity<ProductDto> response = productController.findById(ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenSaveThenReturnSaveResponseEntityProductDto() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/v1/products/{id}")
                .build(ProductBuilder.getProduct().getId());

        when(productService.save(any())).thenReturn(uri);

        ResponseEntity<ProductDto> response = productController.save(ProductBuilder.getProductFormDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/products/1");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void whenUpdateThenReturnUpdateResponseEntityProductDto() {
        when(productService.update(anyLong(), any())).thenReturn(ProductBuilder.getProductDto());

        ResponseEntity<ProductDto> response = productController.update(ProductBuilder.getProduct().getId(),
                ProductBuilder.getProductFormDto());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenDeleteByIdWithSuccess(){
        doNothing().when(productService).deleteById(anyLong());

        ResponseEntity<?> response = productController.deleteById(ProductBuilder.getProduct().getId());

        assertEquals(ResponseEntity.class, response.getClass());
        verify(productService, times(1)).deleteById(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}