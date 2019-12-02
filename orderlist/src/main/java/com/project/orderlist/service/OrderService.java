package com.project.orderlist.service;

import com.project.orderlist.exception.ResourceNotFoundException;
import com.project.orderlist.model.OrderlistModel;
import com.project.orderlist.repository.OrderlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderlistRepository orderlistRepository;
    @Autowired
    public OrderService(OrderlistRepository repo){
        this.orderlistRepository = repo;
    }

    public OrderlistModel createOne(OrderlistModel orderlistModel){
        return orderlistRepository.save(orderlistModel);
    }

    public List<OrderlistModel> createMany(List<OrderlistModel> listOrderlist){
        return orderlistRepository.saveAll(listOrderlist);
    }

    public OrderlistModel getOneOrderlist(Long id){
        return orderlistRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("OrderList","id",id)
                );
    }

    public List<OrderlistModel> getAllOrderlist(){
        return orderlistRepository.findAll();
    }

    public List<OrderlistModel> getAllOrderByOid(String oid){
        return orderlistRepository.findAllByOid(oid);
    }

    public List<OrderlistModel> getAllByName(String name){
        return orderlistRepository.findAllByProductName(name);
    }

    public OrderlistModel updateQuantity(Long id, Integer quantity){
        OrderlistModel orderlist = getOneOrderlist(id);
        orderlist.setQuantity(quantity);
        return orderlistRepository.save(orderlist);
    }

    public String deleteOrderlist(Long id){
        OrderlistModel orderlist = getOneOrderlist(id);
        orderlistRepository.delete(orderlist);
        return "Sucess Delete!!!!";
    }

    public String deleteAllOrderlist(Long oid){
        List<OrderlistModel> orderlist = getAllOrderByOid(Long.toString(oid));
        orderlistRepository.deleteAll(orderlist);
        return "Sucees Delete!!!!!!!!";
    }

}
