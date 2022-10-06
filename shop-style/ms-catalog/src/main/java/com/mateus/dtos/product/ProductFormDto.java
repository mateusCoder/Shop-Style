package com.mateus.dtos.product;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
public class ProductFormDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String brand ;

    private String material;

    @NotNull
    private boolean active;

    @NotNull
    private Long categoryId;
}
