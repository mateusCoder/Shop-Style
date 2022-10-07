package com.mateus.builders;

import com.mateus.dtos.category.CategoryFormDto;
import com.mateus.dtos.category.CategoryGetDto;
import com.mateus.entities.Category;
import com.mateus.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {

    private static final Long id = 1L;

    private static final Long idCategoryParent = 2L;

    private static final String name = "Roupas";

    private static final boolean active = true;

    private static final Long parentId = 2L;

    private static final List<Product> products = new ArrayList<>();

    private static final List<Category> children = new ArrayList<>(List.of(CategoryBuilder.getCategoryChildren()));

    private static final List<CategoryFormDto> childrenFormDto = new ArrayList<>();

    private static final List<CategoryGetDto> childrenGetDto = new ArrayList<>();

    public static Category getCategoryChildren(){
        return Category.builder()
                .id(id)
                .name(name)
                .active(active)
                .parentId(parentId)
                .products(products)
                .children(children)
                .build();
    }

    public static Category getCategoryParent(){
        return Category.builder()
                .id(idCategoryParent)
                .name(name)
                .active(active)
                .products(products)
                .children(children)
                .build();
    }

    public static CategoryFormDto getCategoryFormDto(){
        return CategoryFormDto.builder()
                .id(id)
                .name(name)
                .active(active)
                .parentId(parentId)
                .children(childrenFormDto)
                .build();
    }

    public static CategoryGetDto getCategoryGetDto(){
        return CategoryGetDto.builder()
                .id(id)
                .name(name)
                .active(active)
                .children(childrenGetDto)
                .build();
    }

    public static Page<CategoryGetDto> getCategoryGetDtoPageable(){
        return new PageImpl<>(List.of(getCategoryGetDto()));
    }
}
