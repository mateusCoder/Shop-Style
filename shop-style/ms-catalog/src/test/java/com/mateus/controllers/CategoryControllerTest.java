package com.mateus.controllers;

import com.mateus.builders.CategoryBuilder;
import com.mateus.builders.ProductBuilder;
import com.mateus.dtos.category.CategoryFormDto;
import com.mateus.dtos.category.CategoryGetDto;
import com.mateus.dtos.product.ProductDto;
import com.mateus.services.impl.CategoryServiceImpl;
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
import static org.mockito.Mockito.times;

@AutoConfigureTestDatabase
class CategoryControllerTest {

    @InjectMocks
    CategoryController categoryController;

    @Mock
    CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSaveResponseEntityCategoryDto() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/v1/categories/{id}")
                .build(CategoryBuilder.getCategoryChildren().getId());

        when(categoryService.save(any())).thenReturn(uri);

        ResponseEntity<URI> response = categoryController.save(CategoryBuilder.getCategoryFormDto());

        assertNotNull(response);
        assertEquals(uri.toString(), "http://localhost/v1/categories/1");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void whenFindAllThenReturnResponseEntityPageableCategoryGetDto() {
        when(categoryService.findAll((Pageable) any())).thenReturn(CategoryBuilder.getCategoryGetDtoPageable());

        Pageable page = PageRequest.of(0, 100);
        ResponseEntity<Page<CategoryGetDto>> response = categoryController.findAll(page);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenFindProductByCategoryThenReturnResponseEntityProductDto() {
        when(categoryService.findProductByCategory((Pageable) any(), anyLong())).thenReturn(ProductBuilder.getProductDtoPageable());

        Pageable page = PageRequest.of(0, 100);
        ResponseEntity<Page<ProductDto>> response = categoryController.findProductByCategory(page, ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenUpdateThenReturnUpdateResponseEntityCategoryFormDto() {
        when(categoryService.update(anyLong(), any())).thenReturn(CategoryBuilder.getCategoryFormDto());

        ResponseEntity<CategoryFormDto> response = categoryController.updateCategoryById(
                CategoryBuilder.getCategoryChildren().getId(),
                CategoryBuilder.getCategoryFormDto());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenDeleteCategoryByIdWithSuccess(){
        doNothing().when(categoryService).delete(anyLong());

        ResponseEntity<?> response = categoryController.delete(CategoryBuilder.getCategoryChildren().getId());

        assertEquals(ResponseEntity.class, response.getClass());
        verify(categoryService, times(1)).delete(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}