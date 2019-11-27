package com.project.productlist.entities;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private Long id;
    private String productName;
    private Double price;
}
