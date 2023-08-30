package com.yzecommecer.yzecommecer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzecommecer.yzecommecer.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
