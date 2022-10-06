package com.mateus.services.impl;

import com.mateus.dtos.sku.SkuDto;
import com.mateus.entities.Media;
import com.mateus.entities.Product;
import com.mateus.entities.Sku;
import com.mateus.exceptions.ObjectNotFound;
import com.mateus.repositories.MediaRepository;
import com.mateus.repositories.ProductRepository;
import com.mateus.repositories.SkuRepository;
import com.mateus.services.SkusService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SkusServiceImpl implements SkusService {

    private final SkuRepository skuRepository;

    private final ProductRepository productRepository;

    private final MediaRepository mediaRepository;

    private final ModelMapper mapper;

    @Override
    public URI save(SkuDto skuFormDto) {

        Product product = checkProductValidation(skuFormDto.getProductId());
        List<Media> images = skuFormDto.getImages().stream().map(e -> mapper.map(e, Media.class)).toList();
        mediaRepository.saveAll(images);

        Sku sku = mapper.map(skuFormDto, Sku.class);
        sku.setImages(images);
        skuRepository.save(sku);
        product.getSkus().add(sku);
        productRepository.save(product);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(sku.getId());
    }

    @Override
    public SkuDto update(Long id, SkuDto skuFormDto) {
        Sku sku = checkSkuValidation(id);
        Product product = checkProductValidation(skuFormDto.getProductId());
        mediaRepository.deleteAll(sku.getImages());

        if(sku.getProductId() != product.getId()){
            Product changedProduct = checkProductValidation(sku.getProductId());
            changedProduct.getSkus().remove(sku);
            productRepository.save(changedProduct);
        }

        List<Media> images = skuFormDto.getImages().stream().map(i -> mapper.map(i, Media.class)).toList();
        mediaRepository.saveAll(images);

        sku = mapper.map(skuFormDto, Sku.class);
        sku.setId(id);
        sku.setImages(images);
        skuRepository.save(sku);
        product.getSkus().add(sku);
        productRepository.save(product);

        return mapper.map(sku, SkuDto.class);
    }

    @Override
    public void delete(Long id) {
        Sku sku = checkSkuValidation(id);
        Optional<Product> product = productRepository.findById(sku.getProductId());

        product.get().getSkus().remove(sku);
        productRepository.save(product.get());
        skuRepository.delete(sku);
    }

    public Product checkProductValidation(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Product Not Found!"));
        if (product.isActive()){
            return product;
        }else {
            throw new RuntimeException();
        }
    }

    public Sku checkSkuValidation(Long id){
        return skuRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Sku Not Found!"));
    }
}
