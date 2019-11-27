package com.project.account.model;

import lombok.Data;

import java.util.Date;

@Data
public class Invoice {
    private Long id;
    private Date createAt;
    private Long oid;
}
