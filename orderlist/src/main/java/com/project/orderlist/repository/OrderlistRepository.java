package com.project.orderlist.repository;

import com.project.orderlist.model.OrderlistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderlistRepository extends JpaRepository<OrderlistModel, Long> {
    List<OrderlistModel> findAllByOid(String oid);
    List<OrderlistModel> findAllByProductName(String productName);
}
