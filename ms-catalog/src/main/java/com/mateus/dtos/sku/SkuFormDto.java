package com.mateus.dtos.sku;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class SkuFormDto {

    @NotNull
    private BigDecimal price;

    @NotNull
    private int quantity;

    @NotBlank
    private String color;

    @NotBlank
    private String size;

    @NotNull
    private int height;

    @NotNull
    private int width;

    @NotNull
    private Long productId;
}
