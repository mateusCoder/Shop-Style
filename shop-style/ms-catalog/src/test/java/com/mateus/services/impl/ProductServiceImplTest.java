package com.mateus.services.impl;

import com.mateus.builders.CategoryBuilder;
import com.mateus.builders.ProductBuilder;
import com.mateus.dtos.product.ProductDto;
import com.mateus.entities.Category;
import com.mateus.entities.Product;
import com.mateus.repositories.CategoryRepository;
import com.mateus.repositories.ProductRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindAllThenReturnPageableProductDto() {
        when(productRepository.findAll((Pageable) any())).thenReturn(ProductBuilder.getProductPageable());

        Pageable page = PageRequest.of(0, 100);
        Page<ProductDto> response = productService.findAll(page);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
    }

    @Test
    void whenFindOneThenReturnProductDto(){
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));

        ProductDto response = productService.findById(ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(ProductDto.class, response.getClass());
        assertEquals(ProductBuilder.getProduct().getName(), response.getName());
    }

    @Test
    void whenSaveThenReturnSaveProductDto() {
        MockHttpServletRequest request =new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(CategoryBuilder.getCategoryChildren()));
        when(productRepository.save(any())).thenReturn(ProductBuilder.getProduct());

        URI response = productService.save(ProductBuilder.getProductFormDto());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void whenUpdateThenReturnUpdateProductDto() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(CategoryBuilder.getCategoryChildren()));
        when(productRepository.save(any())).thenReturn(ProductBuilder.getProduct());

        ProductDto response = productService.update(ProductBuilder.getProduct().getId(),
                ProductBuilder.getProductFormDto());

        assertNotNull(response);
        assertEquals(ProductDto.class, response.getClass());
        assertEquals(ProductBuilder.getProductDto().getName(), response.getName());
    }

    @Test
    void whenDeleteByIdWithSuccess() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));
        doNothing().when(productRepository).deleteById(anyLong());

        productService.deleteById(ProductBuilder.getProduct().getId());

        verify(productRepository, times(1)).deleteById(ProductBuilder.getProduct().getId());

    }


    @Test
    void whenFindOneThenReturnCheckProductExistence() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));

        Product response = productService.checkExistenceProduct(ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(Product.class, response.getClass());
        assertEquals(ProductBuilder.getProductDto().getName(), response.getName());
    }

    @Test
    void whenFindOneThenReturnCheckCategoryExistence() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(CategoryBuilder.getCategoryChildren()));

        Category response = productService.checkExistenceCategory(CategoryBuilder.getCategoryChildren().getId());

        assertNotNull(response);
        assertEquals(Category.class, response.getClass());
        assertEquals(CategoryBuilder.getCategoryChildren().getName(), response.getName());
    }
}