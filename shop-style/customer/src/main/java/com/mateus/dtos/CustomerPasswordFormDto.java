package com.mateus.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CustomerPasswordFormDto {

    @Size(max=12,min=6)
    @NotBlank
    private String password;
}
