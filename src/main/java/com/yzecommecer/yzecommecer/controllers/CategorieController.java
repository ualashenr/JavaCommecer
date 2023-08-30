package com.yzecommecer.yzecommecer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzecommecer.yzecommecer.dto.CategoryDTO;
import com.yzecommecer.yzecommecer.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategorieController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	
}
