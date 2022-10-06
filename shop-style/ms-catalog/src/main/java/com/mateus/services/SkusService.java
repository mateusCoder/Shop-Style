package com.mateus.services;

import com.mateus.dtos.sku.SkuDto;
import com.mateus.dtos.sku.SkuFormDto;

import java.net.URI;

public interface SkusService {
    URI save(SkuFormDto skuFormDto);

    SkuDto update(Long id, SkuFormDto skuFormDto);

    void delete(Long id);
}
