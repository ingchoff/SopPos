package com.project.product.services;

import com.project.product.entities.Product;
import com.project.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    private ProductRepository productRepository;


    @Autowired
    private DiscoveryClient discoveryClient;
    public ProductServices(ProductRepository repo) {
        this.productRepository = repo;
    }

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getByName(String name) {
        return productRepository.findByProductName(name);
    }

    public Product createProduct(Product product) {
        Product result = productRepository.save(product);
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("productlistservice");
        String serviceUri = String.format("%s/products/product-stock/add", instances.get(0).getUri().toString());
        restTemplate.postForObject(serviceUri, product, Product.class);
        return result;
    }

    public void createProducts(ArrayList<Product> product) {
        product.forEach((p) -> productRepository.save(p));
    }

    public boolean deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean deleteProductByName(String productName) {
        try {
            Optional<Product> result = productRepository.findByProductName(productName);
            productRepository.deleteById(result.get().getId());
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

    public boolean checkDuplicate(Product body) {
        ArrayList<Product> result = (ArrayList<Product>) productRepository.findAll();
        boolean ans = false;
        ArrayList<String> checklist = new ArrayList<>();
        result.forEach((r) -> {
            checklist.add(r.getProductName());
        });
        ans = !checklist.contains(body.getProductName());
        return ans;
    }
}
