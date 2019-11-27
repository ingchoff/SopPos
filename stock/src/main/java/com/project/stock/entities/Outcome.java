package com.project.stock.entities;

import lombok.Data;

@Data
public class Outcome {
    private Long id;
    private String sku_id;
    private Double quantity;
    private String unit;
    private Double price;
}
