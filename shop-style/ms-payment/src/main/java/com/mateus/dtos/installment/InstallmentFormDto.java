package com.mateus.dtos.installment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InstallmentFormDto {

    @NotNull
    private Integer amount;

    private String brand;

    @NotNull
    private Long paymentId;
}
