package com.yzecommecer.yzecommecer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzecommecer.yzecommecer.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	
}
