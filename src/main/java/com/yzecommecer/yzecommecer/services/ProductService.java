package com.yzecommecer.yzecommecer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzecommecer.yzecommecer.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
		
}
