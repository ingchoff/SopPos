package com.project.invoice.controllers;

import com.project.invoice.entities.Invoice;
import com.project.invoice.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping()
    public List<Invoice> getInvoices() {
        return invoiceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoice(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.getById(id);
        if(!invoice.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    @PostMapping("/create")
    public ResponseEntity<?> postInvoice(@RequestBody Invoice body) {
        Invoice invoice = invoiceService.createInvoice(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }

}
