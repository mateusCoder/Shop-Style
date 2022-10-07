package com.mateus.services.impl;

import com.mateus.builders.CategoryBuilder;
import com.mateus.builders.ProductBuilder;
import com.mateus.dtos.category.CategoryFormDto;
import com.mateus.dtos.category.CategoryGetDto;
import com.mateus.dtos.product.ProductDto;
import com.mateus.entities.Category;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@AutoConfigureTestDatabase
class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSaveCategoryDto() {
        MockHttpServletRequest request =new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(CategoryBuilder.getCategoryChildren()));
        when(categoryRepository.save(any())).thenReturn(CategoryBuilder.getCategoryChildren());

        URI response = categoryService.save(CategoryBuilder.getCategoryFormDto());

        verify(categoryRepository, times(2)).save(any(Category.class));
    }

    @Test
    void findAll() {
        when(categoryRepository.findAll()).thenReturn(List.of(CategoryBuilder.getCategoryChildren()));
        when(categoryRepository.findAllByParentId(anyLong())).thenReturn(List.of(CategoryBuilder.getCategoryParent()));

        Pageable page = PageRequest.of(0, 100);
        Page<CategoryGetDto> response = categoryService.findAll(page);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
    }

    @Test
    void whenFindProductByCategoryThenReturnAllProductsPageableFromACategory() {
        when(productRepository.findAllByCategoryId(anyLong(), (Pageable) any())).thenReturn(ProductBuilder.getProductPageable());

        Pageable page = PageRequest.of(0, 100);
        Page<ProductDto> response = categoryService.findProductByCategory(page, ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
    }

    @Test
    void update() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(CategoryBuilder.getCategoryChildren()));
        when(categoryRepository.findAllByParentId(anyLong())).thenReturn(List.of(new Category()));
        when(productRepository.findAllByCategoryId(anyLong())).thenReturn(List.of(ProductBuilder.getProduct()));
        when(productRepository.save(any())).thenReturn(ProductBuilder.getProduct());
        when(categoryRepository.save(any())).thenReturn(CategoryBuilder.getCategoryChildren());

        CategoryFormDto response = categoryService.update(CategoryBuilder.getCategoryChildren().getId(),
                CategoryBuilder.getCategoryFormDto());
    }

    @Test
    void whenDeleteByIdWithSuccess()  {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(CategoryBuilder.getCategoryChildren()));
        doNothing().when(categoryRepository).delete(any());

        categoryService.delete(CategoryBuilder.getCategoryChildren().getId());

        verify(categoryRepository, times(1)).delete(CategoryBuilder.getCategoryChildren());
    }

    @Test
    void  whenParentCategoryValidationThenReturnAValidCategory(){
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(CategoryBuilder.getCategoryChildren()));

        Category response = categoryService.parentCategoryValidation(CategoryBuilder.getCategoryFormDto());

        assertNotNull(response);
        assertEquals(Category.class, response.getClass());
        assertEquals(CategoryBuilder.getCategoryFormDto().getActive(), response.isActive());
    }

    @Test
    void updateChildren(){
        when(categoryRepository.findAllByParentId(anyLong())).thenReturn(List.of(new Category()));
        when(productRepository.findAllByCategoryId(anyLong())).thenReturn(List.of(ProductBuilder.getProduct()));
        when(productRepository.save(any())).thenReturn(ProductBuilder.getProduct());
        when(categoryRepository.save(any())).thenReturn(CategoryBuilder.getCategoryChildren());

        categoryService.updateChildren(CategoryBuilder.getCategoryChildren());

        verify(categoryRepository, times(1)).save(any(Category.class));
    }
}