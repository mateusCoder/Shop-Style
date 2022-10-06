package com.mateus.services;

import com.mateus.dtos.sku.SkuDto;

import java.net.URI;

public interface SkusService {
    URI save(SkuDto skuFormDto);

    SkuDto update(Long id, SkuDto skuFormDto);

    void delete(Long id);
}
