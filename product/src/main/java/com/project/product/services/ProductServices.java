package com.project.product.services;

import com.project.product.entities.Product;
import com.project.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ProductServices {
    private ProductRepository productRepository;

    @Autowired
    public ProductServices(ProductRepository repo) {
        this.productRepository = repo;
    }

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    public List<Product> getByName(String name) {
        return productRepository.findByProductName(name);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void createProducts(ArrayList<Product> product) {
        List<Product> products = (List<Product>) productRepository.findAll();
        product.forEach((p) -> productRepository.save(p));
    }

    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public Optional<Product> updateProduct(Long id, Product body) {
        Optional<Product> customerOptional = productRepository.findById(id);
        if(!customerOptional.isPresent()) {
            return customerOptional;
        }
        body.setId(id);
        return Optional.of(productRepository.save(body));
    }

    public boolean checkDuplicate(ArrayList<Product> body) {
        ArrayList<Product> result = (ArrayList<Product>) productRepository.findAll();
        ArrayList<String> checkList = new ArrayList<String>();
        boolean ans = false;
        result.forEach((r) -> {
            checkList.add(r.getProductName());
        });
        for (Product p: body) {
            if (checkList.contains(p.getProductName())) {
                ans = false;
                break;
            } else {
                ans = true;
            }
        }
        return ans;
    }
}
