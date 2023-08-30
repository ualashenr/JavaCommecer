package com.yzecommecer.yzecommecer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yzecommecer.yzecommecer.dto.ProductDTO;
import com.yzecommecer.yzecommecer.entities.Product;
import com.yzecommecer.yzecommecer.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable){
		Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
	}
}
