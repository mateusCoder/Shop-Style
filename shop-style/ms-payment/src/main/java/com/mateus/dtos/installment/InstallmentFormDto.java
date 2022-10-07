package com.mateus.dtos.installment;

import javax.validation.constraints.NotNull;

public class InstallmentFormDto {

    @NotNull
    private Integer amount;

    private String brand;

    @NotNull
    private Long paymentId;
}
