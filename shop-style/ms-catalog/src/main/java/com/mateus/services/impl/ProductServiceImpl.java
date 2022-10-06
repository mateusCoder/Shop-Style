package com.mateus.services.impl;

import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.product.ProductFormDto;
import com.mateus.entities.Category;
import com.mateus.entities.Product;
import com.mateus.exceptions.ObjectNotFound;
import com.mateus.repositories.CategoryRepository;
import com.mateus.repositories.ProductRepository;
import com.mateus.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper mapper;

    @Override
    public Page<ProductDto> findAll(Pageable page) {
        Page<Product> products = productRepository.findAll(page);
        return new PageImpl<>(products.stream().map(e -> mapper.map(e, ProductDto.class)).collect(Collectors.toList()));
    }

    @Override
    public ProductDto findById(Long id) {
        return mapper.map(checkExistenceProduct(id), ProductDto.class);
    }

    @Override
    public URI save(ProductFormDto productFormDto) {
        Category category = checkExistenceCategory(productFormDto.getCategoryId());
        Product product = mapper.map(productFormDto, Product.class);
        product.setCategoryId(category.getId());
        productRepository.save(product);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(product.getId());
    }

    @Override
    public ProductDto update(Long id, ProductFormDto productFormDto) {
        Category category = checkExistenceCategory(productFormDto.getCategoryId());
        checkExistenceCategory(id);
        Product product = mapper.map(productFormDto, Product.class);
        product.setCategoryId(category.getId());
        product.setId(id);
        productRepository.save(product);

        return mapper.map(product, ProductDto.class);
    }

    @Override
    public void deleteById(Long id) {
        checkExistenceProduct(id);
        productRepository.deleteById(id);
    }


    public Product checkExistenceProduct(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Product Not Found!"));
    }

    public Category checkExistenceCategory(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Category Not Found!"));
    }
}
