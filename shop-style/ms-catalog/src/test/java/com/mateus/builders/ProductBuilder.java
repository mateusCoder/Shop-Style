package com.mateus.builders;

import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.product.ProductFormDto;
import com.mateus.entities.Product;
import com.mateus.entities.Sku;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {

    private final static Long id = 1L;

    private final static String name = "Camisa Oficial do Fluminense";

    private final static String description = "A camisa pra você que é tricolor de coração";

    private final static String brand = "Umbro" ;

    private final static String material = "Algodão";

    private final static boolean active = true;

    private final static Long categoryId = 1L;

    private final static List<Sku> skus = new ArrayList<>();


    public static Product getProduct(){
        return Product.builder()
                .id(id)
                .name(name)
                .description(description)
                .brand(brand)
                .material(material)
                .active(active)
                .categoryId(categoryId)
                .skus(skus)
                .build();
    }

    public static ProductDto getProductDto(){
        return ProductDto.builder()
                .name(name)
                .description(description)
                .brand(brand)
                .material(material)
                .active(active)
                .categoryId(categoryId)
                .build();
    }

    public static ProductFormDto getProductFormDto(){
        return ProductFormDto.builder()
                .name(name)
                .description(description)
                .brand(brand)
                .material(material)
                .active(active)
                .categoryId(categoryId)
                .build();
    }

    public static Page<Product> getProductPageable(){
        return new PageImpl<>(List.of(getProduct()));
    }

    public static Page<ProductDto> getProductDtoPageable(){
        return new PageImpl<>(List.of(getProductDto()));
    }
}
