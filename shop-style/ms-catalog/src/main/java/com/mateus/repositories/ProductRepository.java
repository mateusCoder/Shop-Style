package com.mateus.repositories;

import com.mateus.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCategoryId(Long id, Pageable pageable);

    List<Product> findAllByCategoryId(Long id);

}
