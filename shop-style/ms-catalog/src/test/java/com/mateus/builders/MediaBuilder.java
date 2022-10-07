package com.mateus.builders;

import com.mateus.dtos.media.MediaDto;
import com.mateus.entities.Media;

public class MediaBuilder {

    private final static Long id = 1L;

    private final static String imageUri = "image.png";

    private final static Long skuId = 1L;

    private String imageUrl = "picture.png";

    public static Media getMedia(){
        return Media.builder()
                .id(id)
                .imageUri(imageUri)
                .skuId(skuId)
                .build();
    }

    public static MediaDto getMediaDto(){
        return MediaDto.builder()
                .id(id)
                .imageUrl(imageUri)
                .build();
    }
}
