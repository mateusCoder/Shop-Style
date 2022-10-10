package com.mateus.controllers;

import com.mateus.dtos.installment.InstallmentDto;
import com.mateus.dtos.installment.InstallmentFormDto;
import com.mateus.services.impl.InstallmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/installments")
public class InstallmentController {

    private final InstallmentServiceImpl instalmentService;

    @PostMapping
    public ResponseEntity<InstallmentDto> save(@Valid @RequestBody InstallmentFormDto installmentFormDto){
        return ResponseEntity.created(instalmentService.save(installmentFormDto)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstallmentDto> update(@PathVariable Long id, @Valid @RequestBody InstallmentFormDto installmentFormDto){
        return ResponseEntity.ok(instalmentService.update(id, installmentFormDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        instalmentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
