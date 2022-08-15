package com.mateus.controllers;

import com.mateus.dtos.AddressDto;
import com.mateus.dtos.AddressFormDto;
import com.mateus.services.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/address")
public class AddressController {

    private final AddressServiceImpl addressService;

    @Transactional
    @PostMapping
    public ResponseEntity<AddressDto> save(@Valid @RequestBody AddressFormDto addressFormDto){
        return ResponseEntity.created(addressService.save(addressFormDto)).build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @Valid @RequestBody AddressFormDto addressFormDto){
        return ResponseEntity.ok(addressService.update(id, addressFormDto));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }
}
