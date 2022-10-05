package com.mateus.services;

import com.mateus.dtos.category.CategoryFormDto;
import com.mateus.dtos.category.CategoryGetDto;
import com.mateus.dtos.product.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

public interface CategoryService {

    URI save(CategoryFormDto categoryFormDto);

    Page<CategoryGetDto> findAll(Pageable pageable);

    Page<ProductDto> findProductByCategory(Pageable pageable, Long id);
}
