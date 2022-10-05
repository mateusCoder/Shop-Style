package com.mateus.services.impl;

import com.mateus.dtos.category.CategoryFormDto;
import com.mateus.dtos.category.CategoryGetDto;
import com.mateus.dtos.product.ProductDto;
import com.mateus.entities.Category;
import com.mateus.entities.Product;
import com.mateus.repositories.CategoryRepository;
import com.mateus.repositories.ProductRepository;
import com.mateus.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Override
    public URI save(CategoryFormDto categoryFormDto) {
        Category parentCategory = parentCategoryValidation(categoryFormDto);
        Category category = mapper.map(categoryFormDto, Category.class);
        categoryRepository.save(category);

        if(parentCategory != null){
            parentCategory.getChildren().add(category);
            categoryRepository.save(parentCategory);
        }

        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(category.getId());
    }

    @Override
    public Page<CategoryGetDto> findAll(Pageable pageable) {
        List<Category> categories = categoryRepository.findAll();
        List<Category> isChildren = new ArrayList<>();
        List<CategoryGetDto> categoriesWithChildren = new ArrayList<>();

        categories.forEach(c -> {
            List<Category> childrenCategories = categoryRepository.findAllByParentId(c.getId());
            c.getChildren().addAll(childrenCategories);
            isChildren.addAll(childrenCategories);
        });

        categories.forEach(c -> {
            if (!isChildren.contains(c)){
                categoriesWithChildren.add(mapper.map(c, CategoryGetDto.class));
            }
        });
        return new PageImpl<>(categoriesWithChildren, pageable, categoriesWithChildren.size());
    }

    @Override
    public Page<ProductDto> findProductByCategory(Pageable pageable, Long id) {
        Page<Product> products = productRepository.findAllByCategoryId(id, pageable);
        return products.map(product -> mapper.map(product, ProductDto.class));

    }

    private Category parentCategoryValidation(CategoryFormDto categoryFormDto){

        if (categoryFormDto.getParentId() != null){
            Category parentCategory = categoryRepository.findById(categoryFormDto.getParentId())
                    .orElseThrow(() -> new RuntimeException());

            if (!parentCategory.isActive()){
                throw new RuntimeException();
            }
            return parentCategory;
        }
        return null;
    }
}
