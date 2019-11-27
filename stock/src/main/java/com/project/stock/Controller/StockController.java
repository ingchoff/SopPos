package com.project.stock.Controller;

import com.project.stock.entities.Stock;
import com.project.stock.services.StockServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    StockServices stockServices;

    @GetMapping()
    public List<Stock> getProducts() {
        return stockServices.getAll();
    }

    @GetMapping(params = "name")
    public ResponseEntity<?> getStock(@RequestParam(value = "name") String name) {
        Optional<Stock> stock = stockServices.getByName(name);
        if(!stock.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stock);
    }

    @PostMapping("/add")
    public ResponseEntity<?> postStock(@Valid @RequestBody Stock body) {
        if (stockServices.checkDuplicate(body)) {
            Stock stock = stockServices.createStock(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(stock);
        } else {
            return ResponseEntity.badRequest().body("มีชื่อวัตถุดิบซ้ำกับข้อมูลทีมีอยู่");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Long id) {
        if (!stockServices.deleteStockById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(params = "name")
    public ResponseEntity<?> deleteStock(@RequestParam(value = "name") String name) {
        if (!stockServices.deleteStockByName(name)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(params = "name")
    public ResponseEntity<?> updateStock(@RequestParam(value = "name") String name, @Valid @RequestBody Stock body) {
        if (!stockServices.updateStock(name, body)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
