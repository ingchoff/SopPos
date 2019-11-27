package com.project.stock.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50, message = "Please provide between 2 - 100")
    private String stockName;

    @NotNull
    @Min(value = 0, message = "Please provide price > 0 unit")
    private double quantity;

    @NotNull
    @Min(value = 0, message = "Please provide price > 0.0 Baht")
    private Double price;

    @NotNull
    @Size(min = 2, max = 50, message = "Please provide between 2 - 100")
    private String type;

    @NotNull
    @Size(min = 2, max = 50, message = "Please provide between 2 - 100")
    private String skuId;
}
