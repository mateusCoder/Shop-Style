package com.mateus.repositories;

import com.mateus.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByParentId(Long id);

    Optional<Category> findByParentId(Long id);

}
