package com.mateus.services;

import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.product.ProductFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

public interface ProductService {
    ProductDto findById(Long id);

    Page<ProductDto> findAll(Pageable page);

    URI save(ProductFormDto productFormDto);

    ProductDto update(Long id, ProductFormDto productFormDto);

    void deleteById(Long id);
}
