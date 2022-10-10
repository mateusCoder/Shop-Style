package com.mateus.dtos.payment;

import com.mateus.entities.Installment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDto {

    private String type;

    private Boolean installments;

    private Boolean active;

    private Installment installment;
}
