package com.project.invoice.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Income {
    private Long id;
    private Long invoice_id;
    private Double price;
    private Date createdAt;

}
