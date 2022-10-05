package com.mateus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean active;

    private Long parentId;

    @OneToMany
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private List<Product> products = new ArrayList<>();

    @Transient
    private List<Category> children = new ArrayList<>();
}
