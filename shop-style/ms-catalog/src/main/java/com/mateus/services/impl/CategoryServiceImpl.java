package com.mateus.services.impl;

import com.mateus.dtos.category.CategoryFormDto;
import com.mateus.dtos.category.CategoryGetDto;
import com.mateus.dtos.product.ProductDto;
import com.mateus.entities.Category;
import com.mateus.entities.Product;
import com.mateus.exceptions.ObjectNotFound;
import com.mateus.repositories.CategoryRepository;
import com.mateus.repositories.ProductRepository;
import com.mateus.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public CategoryFormDto update(Long id, CategoryFormDto categoryFormDto) {
        Category parentCategory = parentCategoryValidation(categoryFormDto);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Category Not Found!"));

        List<Category> childrenCategories = categoryRepository.findAllByParentId(category.getId());
        List<Product> products = productRepository.findAllByCategoryId(category.getId());

        if (parentCategory != null){
            if (parentCategory.getId() != category.getParentId()){
                Optional<Category> oldParent = categoryRepository.findById(category.getParentId());
                oldParent.get().getChildren().remove(category);
                categoryRepository.save(oldParent.get());
            }
        }

        category = mapper.map(categoryFormDto, Category.class);
        category.setId(id);
        category.setChildren(childrenCategories);
        category.setProducts(products);

        updateChildren(category);
        categoryRepository.save(category);

        if (parentCategory != null){
            parentCategory.getChildren().add(category);
            categoryRepository.save(parentCategory);
        }
        return mapper.map(category, CategoryFormDto.class);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Category Not Found!"));
        categoryRepository.delete(category);
    }

    private Category parentCategoryValidation(CategoryFormDto categoryFormDto){

        if (categoryFormDto.getParentId() != null){
            Category parentCategory = categoryRepository.findById(categoryFormDto.getParentId())
                    .orElseThrow(() -> new ObjectNotFound("Category Not Found!"));

            if (!parentCategory.isActive()){
                throw new ObjectNotFound("Category Not Found!");
            }
            return parentCategory;
        }
        return null;
    }

    private void updateChildren(Category category) {

        List<Category> childrenCategory = categoryRepository.findAllByParentId(category.getId());
        List<Product> products = productRepository.findAllByCategoryId(category.getId());

        if (!products.isEmpty()){
            products.forEach(p -> {
                p.setActive(category.isActive());
                productRepository.save(p);
            });
        }

        if (!childrenCategory.isEmpty()){
            childrenCategory.forEach(c -> {
                c.setActive(category.isActive());
                categoryRepository.save(c);
                updateChildren(c);
            });
        }
    }
}
