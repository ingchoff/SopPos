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
    public ResponseEntity<?> getProduct(@RequestParam(value = "name") String name) {
        Optional<Product> product = productServices.getByName(name);
        if(!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<?> postProduct(@Valid @RequestBody Product body) {
        if (productServices.checkDuplicate(body)) {
            Product product = productServices.createProduct(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } else {
            return ResponseEntity.badRequest().body("มีชื่อโปรดักซ้ำกับข้อมูลทีมีอยู่");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (!productServices.deleteProductById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(params = "name")
    public ResponseEntity<?> deleteProduct(@RequestParam(value = "name") String name) {
        if (!productServices.deleteProductByName(name)) {
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
        return ResponseEntity.ok().body(product);
    }
}
