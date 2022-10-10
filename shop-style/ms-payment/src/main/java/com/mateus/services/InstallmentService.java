package com.mateus.services;

import com.mateus.dtos.installment.InstallmentDto;
import com.mateus.dtos.installment.InstallmentFormDto;

import java.net.URI;

public interface InstallmentService {
    URI save(InstallmentFormDto installmentFormDto);

    InstallmentDto update(Long id, InstallmentFormDto installmentFormDto);

    void delete(Long id);
}
