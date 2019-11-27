package com.project.orderlist.controller;

import com.project.orderlist.model.OrderlistModel;
import com.project.orderlist.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orderlist")
public class OrderlistController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public OrderlistModel createOrder(@Valid @RequestBody OrderlistModel orderlistModel){
        return orderService.createOne(orderlistModel);
    }
    @PostMapping("/creates")
    public List<OrderlistModel> createManyOrder(@Valid @RequestBody List<OrderlistModel> orderlistModels){
        return orderService.createMany(orderlistModels);
    }

    @GetMapping("/get")
    public List<OrderlistModel> getAllOrder(){
        return orderService.getAllOrderlist();
    }

    @GetMapping("/get/{id}")
    public OrderlistModel getOrderlist(@PathVariable(name = "id")Long id){
        return orderService.getOneOrderlist(id);
    }
    @GetMapping("/get/oid/{oid}")
    public List<OrderlistModel> getOrderlistByOid(@PathVariable(name = "oid")Long oid){
        return orderService.getAllOrderByOid(oid);
    }

    @GetMapping("/get/name/{name}")
    public List<OrderlistModel> getOrderlistByName(@PathVariable(name = "name")String name){
        return orderService.getAllByName(name);
    }

    @PutMapping("/update")
    public OrderlistModel updateOrderlist(@RequestBody OrderlistModel orderlistModel){
        return orderService.updateQuantity(orderlistModel.getId(), orderlistModel.getQuantity());
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrderlist(@PathVariable(name = "id")Long id){
        return orderService.deleteOrderlist(id);
    }

    @DeleteMapping("/delete/oid/{oid}")
    public String deleteAllOidOrderlist(@PathVariable(name = "oid")Long oid){
        return orderService.deleteAllOrderlist(oid);
    }


}
