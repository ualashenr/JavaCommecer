package com.yzecommecer.yzecommecer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yzecommecer.yzecommecer.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT obj "
			+ "FROM Product obj "
			+ "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
	Page<Product> searchByName(String name, Pageable pageable);
	
}
