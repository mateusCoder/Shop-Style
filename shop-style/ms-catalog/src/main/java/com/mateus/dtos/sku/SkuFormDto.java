package com.mateus.dtos.sku;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mateus.dtos.media.MediaDto;
import com.mateus.entities.Media;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SkuFormDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private BigDecimal price;

    @NotNull
    private int quantity;

    @NotBlank
    private String color;

    @NotBlank
    private String size;

    @NotNull
    private Integer height;

    @NotNull
    private Integer width;

    @NotNull
    private List<MediaDto> images;

    @NotNull
    private Long productId;

}
