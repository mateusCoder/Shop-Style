package com.mateus.controllers;

import com.mateus.dtos.category.CategoryFormDto;
import com.mateus.dtos.category.CategoryGetDto;
import com.mateus.dtos.product.ProductDto;
import com.mateus.services.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<URI> save(@RequestBody CategoryFormDto categoryFormDto){
        return ResponseEntity.created(categoryService.save(categoryFormDto)).build();
    }

    @GetMapping
    public ResponseEntity<Page<CategoryGetDto>> findAll(@PageableDefault(sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<ProductDto>> findProductByCategory(@PageableDefault(sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable, @PathVariable(required = true) Long id){
        return ResponseEntity.ok(categoryService.findProductByCategory(pageable, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryFormDto> updateCategoryById(@PathVariable Long id, @RequestBody @Valid CategoryFormDto categoryFormDto){
        return ResponseEntity.ok(categoryService.update(id, categoryFormDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
