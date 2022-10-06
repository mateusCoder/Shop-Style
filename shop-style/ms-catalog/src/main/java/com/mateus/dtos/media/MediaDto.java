package com.mateus.dtos.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String imageUrl;
}
