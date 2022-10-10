package com.mateus.services.impl;

import com.mateus.dtos.payment.PaymentDto;
import com.mateus.dtos.payment.PaymentFormDto;
import com.mateus.entities.Payment;
import com.mateus.repositories.PaymentRepository;
import com.mateus.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final ModelMapper mapper;

    @Override
    public URI save(PaymentFormDto paymentFormDto) {
        Payment payment = mapper.map(paymentFormDto, Payment.class);
        paymentRepository.save(payment);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(payment.getId());
    }

    @Override
    public Page<PaymentDto> findAll(Pageable pageable) {
        Page<Payment> payments = paymentRepository.findAll(pageable);
        return payments.map(e -> mapper.map(e, PaymentDto.class));
    }

    @Override
    public PaymentDto update(Long id, PaymentFormDto paymentFormDto) {
        Payment payment = mapper.map(paymentFormDto, Payment.class);
        payment.setId(id);
        paymentRepository.save(payment);
        return mapper.map(payment, PaymentDto.class);
    }

    @Override
    public void delete(Long id) {
        checkPaymentExistence(id);
        paymentRepository.deleteById(id);
    }

    public Payment checkPaymentExistence(Long id){
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

}
