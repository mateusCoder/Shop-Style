package com.mateus.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentFormDto {

    @NotBlank
    private String type;

    @NotNull
    private Boolean installments;

    @NotNull
    private Boolean active;
}
