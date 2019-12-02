package com.project.invoice.entities;

import lombok.Data;

@Data
public class OrderlistModel {

    private Long id;

    private String productName;

    private String oid;

    private Integer quantity;
}
