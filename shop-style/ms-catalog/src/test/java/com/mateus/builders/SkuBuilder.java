package com.mateus.builders;

import com.mateus.dtos.media.MediaDto;
import com.mateus.dtos.sku.SkuDto;
import com.mateus.entities.Media;
import com.mateus.entities.Sku;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SkuBuilder {

    private static final Long id = 1L;

    private static final BigDecimal price = BigDecimal.valueOf(259.99);

    private static final int quantity = 20;

    private static final String color = "vermelho";

    private static final String size = "M";

    private static final Integer height = 250;

    private static final Integer width = 80;

    private static final Long productId = 1L;

    private static final List<Media> images = new ArrayList<>();

    private static final List<MediaDto> imagesMediaDto = new ArrayList<>();


    public static Sku getSku(){
        return Sku.builder()
                .id(id)
                .price(price)
                .quantity(quantity)
                .color(color)
                .size(size)
                .height(height)
                .width(width)
                .productId(productId)
                .images(images)
                .build();
    }

    public static SkuDto getSkuDto(){
        return SkuDto.builder()
                .id(id)
                .price(price)
                .quantity(quantity)
                .color(color)
                .size(size)
                .height(height)
                .width(width)
                .productId(productId)
                .images(imagesMediaDto)
                .build();
    }
}
