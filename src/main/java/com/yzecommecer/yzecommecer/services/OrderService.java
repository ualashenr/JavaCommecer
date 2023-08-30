package com.yzecommecer.yzecommecer.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yzecommecer.yzecommecer.dto.OrderDTO;
import com.yzecommecer.yzecommecer.dto.OrderItemDTO;
import com.yzecommecer.yzecommecer.entities.Order;
import com.yzecommecer.yzecommecer.entities.OrderItem;
import com.yzecommecer.yzecommecer.entities.OrderStatus;
import com.yzecommecer.yzecommecer.entities.Product;
import com.yzecommecer.yzecommecer.entities.User;
import com.yzecommecer.yzecommecer.repositories.OrderItemRepository;
import com.yzecommecer.yzecommecer.repositories.OrderRepository;
import com.yzecommecer.yzecommecer.repositories.ProductRepository;
import com.yzecommecer.yzecommecer.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new OrderDTO(order);
	}

	@Transactional()
	public OrderDTO insert(OrderDTO dto){
		
		Order order = new Order();
		
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		
		User user = userService.authenticated();
		order.setClient(user);
		
		for(OrderItemDTO itemDto : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
			order.getItems().add(item);
		}
		
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		return new OrderDTO(order);
	}
	
	
}
