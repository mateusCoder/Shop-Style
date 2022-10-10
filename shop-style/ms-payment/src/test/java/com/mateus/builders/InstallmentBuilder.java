package com.mateus.builders;

import com.mateus.dtos.installment.InstallmentDto;
import com.mateus.dtos.installment.InstallmentFormDto;
import com.mateus.entities.Installment;

public class InstallmentBuilder {

    private static final Long id = 1L;

    private static final Integer amount = 50;

    private static final String brand = "Nike";

    private static final Long paymentId = 1L;

    public static Installment getInstallment(){
        return Installment.builder()
                .id(id)
                .amount(amount)
                .brand(brand)
                .paymentId(paymentId)
                .build();
    }

    public static InstallmentDto getInstallmentDto(){
        return InstallmentDto.builder()
                .amount(amount)
                .brand(brand)
                .paymentId(paymentId)
                .build();
    }

    public static InstallmentFormDto getInstallmentFormDto(){
        return InstallmentFormDto.builder()
                .amount(amount)
                .brand(brand)
                .paymentId(paymentId)
                .build();
    }
}
