package com.mateus.dtos.sku;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SkuDto {

    private BigDecimal price;

    private int quantity;

    private String color;

    private String size;

    private Integer height;

    private Integer width;

    private Long productId;
}
