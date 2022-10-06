package com.mateus.controllers;

import com.mateus.dtos.sku.SkuDto;
import com.mateus.services.impl.SkusServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/skus")
public class SkusController {

    private final SkusServiceImpl skusService;

    @PostMapping
    public ResponseEntity<URI> save(@Valid @RequestBody SkuDto skuDto){
        return ResponseEntity.created(skusService.save(skuDto)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkuDto> update(@Valid @PathVariable Long id, @RequestBody SkuDto skuFormDto){
        return ResponseEntity.ok(skusService.update(id, skuFormDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        skusService.delete(id);
        return ResponseEntity.ok().build();
    }

}
