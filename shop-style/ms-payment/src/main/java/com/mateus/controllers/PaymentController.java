package com.mateus.controllers;

import com.mateus.dtos.payment.PaymentDto;
import com.mateus.dtos.payment.PaymentFormDto;
import com.mateus.services.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> save(@Valid @RequestBody PaymentFormDto paymentFormDto){
        return ResponseEntity.created(paymentService.save(paymentFormDto)).build();
    }

    @GetMapping
    public ResponseEntity<Page<PaymentDto>> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC)
                                                        Pageable pageable){
        return ResponseEntity.ok(paymentService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> update(@PathVariable Long id, @Valid @RequestBody PaymentFormDto paymentFormDto){
        return ResponseEntity.ok(paymentService.update(id, paymentFormDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        paymentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
