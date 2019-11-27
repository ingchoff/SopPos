package com.project.order.service;

import com.netflix.discovery.DiscoveryClient;
import com.project.order.exception.ResourceNotFoundException;
import com.project.order.model.InvoiceModel;
import com.project.order.model.OrderModel;
import com.project.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class Orderservice {
    private OrderRepository orderRepository;
    private SimpleDateFormat dateFormat;

    public Orderservice(OrderRepository repo){
        this.orderRepository = repo;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public OrderModel createOrder(OrderModel order){
        return orderRepository.save(order);
    }

    public List<OrderModel> getAll(){
        return orderRepository.findAll();
    }

    public OrderModel getById(Long id){
        return orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order", "id",id));
    }

    public List<OrderModel> getDateOrder(Integer str, String date){
        List<OrderModel> allOrder = getAll();
        List<OrderModel> listOrder = new ArrayList<OrderModel>();
        for (OrderModel temp: allOrder) {
            if (dateFormat.format(temp.getCreatedAt()).substring(0, str).equals(date)){
                listOrder.add(temp);
            }
        }
        return listOrder;
    }

    public OrderModel updateOrder(InvoiceModel invoiceModel, String status){
        OrderModel order = getById(invoiceModel.getOid());
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public String deleteOrder(Long id){
        orderRepository.deleteById(id);
        return "Sucess Delete!!!!!";
    }


}
