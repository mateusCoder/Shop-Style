package com.mateus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @ManyToOne
    @JoinColumn(name="Product_id")
    private Product productId;
}
