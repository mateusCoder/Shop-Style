package com.mateus.services;

import com.mateus.dtos.payment.PaymentDto;
import com.mateus.dtos.payment.PaymentFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

public interface PaymentService {

    Page<PaymentDto> findAll(Pageable pageable);

    URI save(PaymentFormDto paymentFormDto);

    PaymentDto update(Long id, PaymentFormDto paymentFormDto);

    void delete(Long id);

}
