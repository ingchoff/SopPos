package com.project.productlist.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "productlists")
public class ProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50, message = "Please provide between 2 - 100")
    private String productName;

    @Size(min = 2, max = 50, message = "Please provide between 2 - 100")
    private String unit;

    @Size(min = 2, max = 50, message = "Please provide between 2 - 100")
    private String skuId;

    @Min(value = 0, message = "Please provide price > 0.0 Baht")
    private Integer quantity;
}
