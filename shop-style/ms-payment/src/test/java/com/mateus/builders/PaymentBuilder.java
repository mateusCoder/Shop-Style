package com.mateus.builders;

import com.mateus.entities.Installment;
import com.mateus.entities.Payment;

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
}
