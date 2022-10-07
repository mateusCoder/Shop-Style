package com.mateus.dtos.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String imageUrl;
}
