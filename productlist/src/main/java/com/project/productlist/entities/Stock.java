package com.project.productlist.entities;

import lombok.Data;

@Data
public class Stock {
    private Long id;
    private String stockName;
    private double quantity;
    private String type;
    private String skuId;
}
