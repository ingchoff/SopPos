package com.project.order.model;

import lombok.Data;

@Data
public class OrderListModel {
    private Long id;
    private String productName;
    private Long oid;
    private Integer quantity;
}
