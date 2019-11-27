package com.project.productlist.controllers;

import com.project.productlist.entities.Product;
import com.project.productlist.entities.ProductList;
import com.project.productlist.services.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/product-stock")
public class ProductListController {

    @Autowired
    ProductListService productListService;

    @GetMapping()
    public List<ProductList> getProducts() {
        return productListService.getAll();
    }

    @GetMapping(params = "name")
    public ResponseEntity<?> getProduct(@RequestParam(value = "name") String name) {
        ArrayList<ProductList> product = productListService.getByName(name);
        if(product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProductList(@Valid @RequestBody ProductList body) {
        productListService.createProductList(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @DeleteMapping(params = "name")
    public ResponseEntity<?> deleteProductList(@RequestParam(value = "name") String name) {
        if (!productListService.deleteProductListByName(name)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(params = "name")
    public ResponseEntity<?> updateProductList(@RequestParam(value = "name") String name, @RequestBody ProductList productlist) {
        if (!productListService.updateProductList(name, productlist)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
