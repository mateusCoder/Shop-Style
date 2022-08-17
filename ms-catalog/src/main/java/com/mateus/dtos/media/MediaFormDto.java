package com.mateus.dtos.media;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
public class MediaFormDto {

    private String images;

    private Long skuId;
}
