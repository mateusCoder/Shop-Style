package com.mateus.builders;

import com.mateus.dtos.payment.PaymentDto;
import com.mateus.dtos.payment.PaymentFormDto;
import com.mateus.entities.Installment;
import com.mateus.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class PaymentBuilder {

    private static final Long id= 1L;

    private static final String type = "Camiseta";

    private static final Boolean installments = true;

    private static final Boolean active = true;

    private static final Installment installment = InstallmentBuilder.getInstallment();

    public static Payment getPayment(){
        return Payment.builder()
                .id(id)
                .type(type)
                .installments(installments)
                .active(active)
                .installment(installment)
                .build();
    }

    public static PaymentDto getPaymentDto(){
        return PaymentDto.builder()
                .type(type)
                .installments(installments)
                .active(active)
                .installment(installment)
                .build();
    }

    public static PaymentFormDto getPaymentFormDto(){
        return PaymentFormDto.builder()
                .type(type)
                .installments(installments)
                .active(active)
                .build();
    }

    public static Page<Payment> getPaymentPageable(){
        return new PageImpl<>(List.of(getPayment()));
    }
}
