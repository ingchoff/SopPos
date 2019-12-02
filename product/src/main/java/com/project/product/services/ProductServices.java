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

import java.lang.reflect.Array;
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
            RestTemplate restTemplate = new RestTemplate();
            List<ServiceInstance> instances = discoveryClient.getInstances("productlistservice");
            String serviceUri = String.format("%s/api/products/product-stock?name=%s", instances.get(0).getUri().toString(), productName);
            restTemplate.exchange(serviceUri, HttpMethod.DELETE, null, Product.class, productName);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean updateProduct(String productName, Product body) {
        try {
            Optional<Product> result = productRepository.findByProductName(productName);
            body.setId(result.get().getId());
            Optional.of(productRepository.save(body));
            RestTemplate restTemplate = new RestTemplate();
            List<ServiceInstance> instances = discoveryClient.getInstances("productlistservice");
            String serviceUri = String.format("%s/api/products/product-stock?name=%s", instances.get(0).getUri().toString(), productName);
            restTemplate.postForObject(serviceUri, body, Product.class);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
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

    public boolean checkDuplicateArray(ArrayList<Product> body) {
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
