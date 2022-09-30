package com.mateus.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {

    private String name;

    private String description;

    private String brand ;

    private String material;

    private boolean active;

    private Long categoryId;
}
