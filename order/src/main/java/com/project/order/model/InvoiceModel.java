package com.project.order.model;

import lombok.Data;

import java.util.Date;

@Data
public class InvoiceModel {
    private Long id;
    private Date createAt;
    private Long oid;
}
