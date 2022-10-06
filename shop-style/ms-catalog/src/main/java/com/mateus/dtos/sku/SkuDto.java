package com.mateus.dtos.sku;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mateus.dtos.media.MediaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SkuDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private BigDecimal price;

    private int quantity;

    private String color;

    private String size;

    private Integer height;

    private Integer width;

    private List<MediaDto> images;

    private Long productId;
}