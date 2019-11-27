package com.project.productlist.repositories;

import com.project.productlist.entities.ProductList;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface ProductListRepository extends CrudRepository<ProductList, Long> {
    ArrayList<ProductList> findByProductName(String name);
}
