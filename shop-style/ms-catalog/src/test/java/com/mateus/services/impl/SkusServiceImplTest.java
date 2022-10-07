package com.mateus.services.impl;

import com.mateus.builders.CategoryBuilder;
import com.mateus.builders.MediaBuilder;
import com.mateus.builders.ProductBuilder;
import com.mateus.builders.SkuBuilder;
import com.mateus.dtos.product.ProductDto;
import com.mateus.dtos.sku.SkuDto;
import com.mateus.entities.Media;
import com.mateus.entities.Product;
import com.mateus.entities.Sku;
import com.mateus.repositories.MediaRepository;
import com.mateus.repositories.ProductRepository;
import com.mateus.repositories.SkuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
class SkusServiceImplTest {

    @InjectMocks
    SkusServiceImpl skusService;

    @Mock
    SkuRepository skuRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    MediaRepository mediaRepository;

    @Spy
    ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSaveSkuDto() {
        MockHttpServletRequest request =new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(skuRepository.findById(anyLong())).thenReturn(Optional.of(SkuBuilder.getSku()));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));
        when(mediaRepository.saveAll(any())).thenReturn((List.of(MediaBuilder.getMedia())));
        when(skuRepository.save(any())).thenReturn(SkuBuilder.getSku());

        URI response = skusService.save(SkuBuilder.getSkuDto());

        verify(skuRepository, times(1)).save(any(Sku.class));
    }

    @Test
    void update() {
        when(skuRepository.findById(anyLong())).thenReturn(Optional.of(SkuBuilder.getSku()));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));
        when(mediaRepository.saveAll(any())).thenReturn((List.of(MediaBuilder.getMedia())));
        when(skuRepository.save(any())).thenReturn(SkuBuilder.getSku());

        SkuDto response = skusService.update(SkuBuilder.getSku().getId(), SkuBuilder.getSkuDto());

        assertNotNull(response);
        assertEquals(SkuDto.class, response.getClass());
        assertEquals(SkuBuilder.getSku().getId(), response.getId());
    }

    @Test
    void delete() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));
        when(skuRepository.findById(anyLong())).thenReturn(Optional.of(SkuBuilder.getSku()));
        doNothing().when(skuRepository).delete(any());

        skusService.delete(SkuBuilder.getSku().getId());

        verify(skuRepository, times(1)).delete(SkuBuilder.getSku());
    }

    @Test
    void checkProductValidation() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductBuilder.getProduct()));

        Product response = skusService.checkProductValidation(ProductBuilder.getProduct().getId());

        assertNotNull(response);
        assertEquals(Product.class, response.getClass());
        assertEquals(ProductBuilder.getProductDto().getName(), response.getName());
    }

    @Test
    void checkSkuValidation() {
        when(skuRepository.findById(anyLong())).thenReturn(Optional.of(SkuBuilder.getSku()));

        Sku response = skusService.checkSkuValidation(SkuBuilder.getSku().getId());

        assertNotNull(response);
        assertEquals(Sku.class, response.getClass());
        assertEquals(SkuBuilder.getSku().getId(), response.getId());
    }
}