package com.mateus.controllers;

import com.mateus.dtos.sku.SkuDto;
import com.mateus.dtos.sku.SkuFormDto;
import com.mateus.services.impl.SkusServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/skus")
public class SkusController {

    private final SkusServiceImpl skusService;

    @PostMapping
    public ResponseEntity<URI> save(@RequestBody SkuFormDto skuFormDto){
        return ResponseEntity.created(skusService.save(skuFormDto)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkuDto> update(@PathVariable Long id, @RequestBody SkuFormDto skuFormDto){
        return ResponseEntity.ok(skusService.update(id, skuFormDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        skusService.delete(id);
        return ResponseEntity.ok().build();
    }

}
