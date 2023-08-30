package com.yzecommecer.yzecommecer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzecommecer.yzecommecer.entities.OrderItem;
import com.yzecommecer.yzecommecer.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
