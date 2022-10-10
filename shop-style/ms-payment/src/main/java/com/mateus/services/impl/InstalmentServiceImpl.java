package com.mateus.services.impl;

import com.mateus.dtos.installment.InstallmentDto;
import com.mateus.dtos.installment.InstallmentFormDto;
import com.mateus.entities.Installment;
import com.mateus.entities.Payment;
import com.mateus.repositories.InstallmentRepository;
import com.mateus.repositories.PaymentRepository;
import com.mateus.services.InstallmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class InstalmentServiceImpl implements InstallmentService {

    private final InstallmentRepository installmentRepository;

    private final PaymentRepository paymentRepository;

    private final ModelMapper mapper;
    @Override
    public URI save(InstallmentFormDto installmentFormDto) {
        Payment payment = checkPaymentExistence(installmentFormDto.getPaymentId());
        Installment installment = mapper.map(installmentFormDto, Installment.class);
        installmentRepository.save(installment);
        payment.setInstallment(installment);
        paymentRepository.save(payment);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(installment.getId());
    }

    @Override
    public InstallmentDto update(Long id, InstallmentFormDto installmentFormDto) {
        Installment oldInstallment = checkInstallmentExistence(id);
        if(installmentFormDto.getPaymentId() != oldInstallment.getPaymentId()){
            Payment payment = checkPaymentExistence(oldInstallment.getId());
            payment.setInstallment(null);
            paymentRepository.save(payment);
        }
        Payment payment = checkPaymentExistence(installmentFormDto.getPaymentId());
        Installment installment = mapper.map(installmentFormDto, Installment.class);
        installment.setId(id);
        installmentRepository.save(installment);
        payment.setInstallment(installment);
        paymentRepository.save(payment);
        return mapper.map(installment, InstallmentDto.class);
    }

    @Override
    public void delete(Long id) {
        Installment installment = checkInstallmentExistence(id);
        Payment payment = checkPaymentExistence(installment.getPaymentId());
        payment.setInstallment(null);
        paymentRepository.save(payment);
        installmentRepository.deleteById(id);
    }

    public Payment checkPaymentExistence(Long id){
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException());
        if (payment.getInstallments()){
            return payment;
        }else{
            throw new RuntimeException();
        }
    }

    public Installment checkInstallmentExistence(Long id){
        return installmentRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
