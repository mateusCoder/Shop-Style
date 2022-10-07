package com.mateus.dtos.installment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InstallmentDto {

    private Integer amount;

    private String brand;

    private Long paymentId;
}
