package com.mateus.controllers;

import com.mateus.dtos.CustomerDto;
import com.mateus.dtos.CustomerFormDto;
import com.mateus.dtos.CustomerFormDtoWithNoPassword;
import com.mateus.dtos.CustomerPasswordFormDto;
import com.mateus.services.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @Transactional
    @PostMapping
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody CustomerFormDto customerForm){
        return ResponseEntity.created(customerService.save(customerForm)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.findById(id));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody CustomerFormDtoWithNoPassword customForm, @PathVariable Long id){
        return ResponseEntity.ok(customerService.update(customForm, id));
    }

    @Transactional
    @PutMapping("/{id}/password")
    public ResponseEntity<CustomerDto> updatePassword(@PathVariable Long id, @Valid @RequestBody CustomerPasswordFormDto passwordFormDto){
       CustomerDto response = customerService.updatePassword(id, passwordFormDto);
        return ResponseEntity.ok(response);
    }
}
