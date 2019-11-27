package com.project.order.controller;


import com.project.order.model.InvoiceModel;
import com.project.order.model.OrderModel;
import com.project.order.service.Orderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    Orderservice orderservice;

    @PostMapping("/create")
    public OrderModel createOrder(@Valid @RequestBody OrderModel orderModel){
        return orderservice.createOrder(orderModel);
    }

    @GetMapping("/get")
    public List<OrderModel> getAllOrder(){
        return orderservice.getAll();
    }

    @GetMapping("/get/{id}")
    public OrderModel getById(@PathVariable(name = "id")Long id){
        return orderservice.getById(id);
    }

    @GetMapping("/get/date/{date}")
    public List<OrderModel> getByDate(@PathVariable(name = "date")String date){
        return orderservice.getDateOrder(10, date);
    }

    @GetMapping("/get/month/{month}")
    public List<OrderModel> getByMonth(@PathVariable(name = "month")String month){
        return orderservice.getDateOrder(10, month);
    }

    @GetMapping("/get/year/{year}")
    public List<OrderModel> getByYear(@PathVariable(name = "year")String year){
        return orderservice.getDateOrder(10, year);
    }

    @PostMapping("/update")
    public OrderModel updateOrder(@Valid @RequestBody InvoiceModel invoiceModel){
        return orderservice.updateOrder(invoiceModel, "done");
    }
}
