package com.yzecommecer.yzecommecer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yzecommecer.yzecommecer.dto.ProductDTO;
import com.yzecommecer.yzecommecer.dto.ProductMinDTO;
import com.yzecommecer.yzecommecer.entities.Product;
import com.yzecommecer.yzecommecer.repositories.ProductRepository;
import com.yzecommecer.yzecommecer.services.exceptions.DatabaseException;
import com.yzecommecer.yzecommecer.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProductMinDTO> findAll(Pageable pageable){
		Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductMinDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product product = repository.findById(id).get();
		return new ProductDTO(product);
	}
	
	@Transactional()
	public ProductDTO insert(ProductDTO product){
		Product entity = new Product();
		copyDtoToEntity(product, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}
	
	@Transactional()
	public ProductDTO update(Long id, ProductDTO product) {
		try {
			Product entity = repository.getReferenceById(id);
			copyDtoToEntity(product, entity);
			repository.save(entity);
			return new ProductDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
		repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}
	
	private void copyDtoToEntity(ProductDTO product, Product entity) {
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setPrice(product.getPrice());
		entity.setImgUrl(product.getImgUrl());
	}
}

