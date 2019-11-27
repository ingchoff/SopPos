package com.project.stock.services;

import com.project.stock.entities.Stock;
import com.project.stock.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class StockServices {
    private StockRepository stockRepository;


    @Autowired
    private DiscoveryClient discoveryClient;
    public StockServices(StockRepository repo) {
        this.stockRepository = repo;
    }

    public List<Stock> getAll() {
        return (List<Stock>) stockRepository.findAll();
    }

    public Optional<Stock> getByName(String name) {
        return stockRepository.findByStockName(name);
    }


    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
//        RestTemplate restTemplate = new RestTemplate();
//        List<ServiceInstance> instances = discoveryClient.getInstances("productlistservice");
//        String serviceUri = String.format("%s/products/product-stock/add", instances.get(0).getUri().toString());
//        restTemplate.postForObject(serviceUri, product, Product.class);
    }

    public void createProducts(ArrayList<Stock> product) {
        product.forEach((p) -> stockRepository.save(p));
    }

    public boolean deleteStockById(Long id) {
        try {
            stockRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean deleteStockByName(String stockName) {
        try {
            Optional<Stock> result = stockRepository.findByStockName(stockName);
            stockRepository.deleteById(result.get().getId());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean updateStock(String stockName, Stock body) {
        try {
            Optional<Stock> result = stockRepository.findByStockName(stockName);
            body.setId(result.get().getId());
            String skuIdTmp = result.get().getSkuId();
            Optional.of(stockRepository.save(body));
            RestTemplate restTemplate = new RestTemplate();
            List<ServiceInstance> instances = discoveryClient.getInstances("productlistservice");
            String serviceUri = String.format("%s/api/products/product-stock?skuid=%s", instances.get(0).getUri().toString(), skuIdTmp);
            restTemplate.postForObject(serviceUri, body, Stock.class);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean checkDuplicate(Stock body) {
        ArrayList<Stock> result = (ArrayList<Stock>) stockRepository.findAll();
        boolean ans = false;
        ArrayList<String> checklist = new ArrayList<>();
        result.forEach((r) -> {
            checklist.add(r.getStockName());
        });
        ans = !checklist.contains(body.getStockName());
        return ans;
    }
}
