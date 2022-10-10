package com.mateus.builders;

import com.mateus.entities.Installment;

public class PaymentBuilder {

    private static final Long id= 1L;

    private static final String type = "Camiseta";

    private static final Boolean installments = true;

    private static final Boolean active = true;

    private static final Installment installment = InstallmentBuilder.getInstallment();
}
