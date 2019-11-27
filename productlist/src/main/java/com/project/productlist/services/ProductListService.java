package com.project.productlist.services;

import com.project.productlist.entities.Product;
import com.project.productlist.entities.ProductList;
import com.project.productlist.repositories.ProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductListService {
    private ProductListRepository productListRepository;

    @Autowired
    public ProductListService(ProductListRepository repo) {
        this.productListRepository = repo;
    }

    public List<ProductList> getAll() {
        return (List<ProductList>) productListRepository.findAll();
    }

    public ArrayList<ProductList> getByName(String name) {
        return productListRepository.findByProductName(name);
    }

    public void createProductList(ProductList productList) {
        productListRepository.save(productList);
    }

    public boolean deleteProductListByName(String productName) {
        try {
            ArrayList<ProductList> result = productListRepository.findByProductName(productName);
            for (ProductList pl: result) {
                productListRepository.delete(pl);
            }
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean updateProductList(String productName, Product product) {
        try {
            ArrayList<ProductList> productArray = productListRepository.findByProductName(productName);
            for (ProductList pl: productArray) {
                pl.setProductName(product.getProductName());
                productListRepository.save(pl);
            }
            return true;
        } catch (EmptyResultDataAccessException e) {
            return  false;
        }
    }

}
