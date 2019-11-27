package com.project.product.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50, message = "Please provide between 2 - 100")
    private String productName;

    @NotNull
    @Min(value = 0, message = "Please provide price > 0.0 Baht")
    private Double price;
}
