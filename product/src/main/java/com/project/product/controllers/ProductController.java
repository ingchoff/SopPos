package com.project.product.controllers;

import com.project.product.services.ProductServices;
import com.project.product.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductServices productServices;

    @GetMapping()
    public List<Product> getProducts() {
        return productServices.getAll();
    }

    @GetMapping(params = "name")
    public List<Product> getProduct(@RequestParam(value = "name") String name) {
        return productServices.getByName(name);
    }

    @PostMapping()
    public ResponseEntity<?> postCustomer(@Valid @RequestBody ArrayList<Product> body) {
        if (productServices.checkDuplicate(body)) {
            productServices.createProducts(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } else {
            return ResponseEntity.badRequest().body("มีชื่อโปรดักซ้ำกับข้อมูลที่มีอยู่");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (!productServices.deleteProduct(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody Product body) {
        Optional<Product> product = productServices.updateProduct(id, body);
        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
