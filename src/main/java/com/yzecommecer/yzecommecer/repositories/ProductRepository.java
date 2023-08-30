package com.yzecommecer.yzecommecer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzecommecer.yzecommecer.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
