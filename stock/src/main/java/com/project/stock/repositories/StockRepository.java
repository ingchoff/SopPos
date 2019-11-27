package com.project.stock.repositories;

import com.project.stock.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByStockName(String name);
    Optional<Stock> findBySkuId(String skuId);
}