package com.mateus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Sku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    private int quantity;

    private String color;

    private String size;

    private Integer height;

    private Integer width;

    private Long productId;

    @OneToMany
    @JoinColumn(name = "skuId", referencedColumnName = "id")
    private List<Media> images;
}
