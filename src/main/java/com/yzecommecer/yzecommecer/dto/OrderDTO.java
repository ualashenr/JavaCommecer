package com.yzecommecer.yzecommecer.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.yzecommecer.yzecommecer.entities.Order;
import com.yzecommecer.yzecommecer.entities.OrderItem;
import com.yzecommecer.yzecommecer.entities.OrderStatus;

import jakarta.validation.constraints.NotEmpty;

public class OrderDTO {
	
	private Long id;
	private Instant moment;
	private OrderStatus status;
	private UserMinDTO client;
	private PaymentDTO payment;
	
	@NotEmpty(message = "Deve ter pelo menos um item")
	private List<OrderItemDTO> items = new ArrayList<>();
	
	public OrderDTO(Long id, Instant moment, OrderStatus status, UserMinDTO client, PaymentDTO payment) {
		this.id = id;
		this.moment = moment;
		this.status = status;
		this.client = client;
		this.payment = payment;
	}
	
	public OrderDTO(Order entity) {
		id = entity.getId();
		moment = entity.getMoment();
		status = entity.getStatus();
		client = new UserMinDTO(entity.getClient());
		payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
		for(OrderItem x : entity.getItems()) {
			OrderItemDTO itemDto = new OrderItemDTO(x);
			items.add(itemDto);
		}
	}

	public Long getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public UserMinDTO getClient() {
		return client;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for(OrderItemDTO item : items) {
			sum += item.getSubTotal();
		}
		return sum;
	}
	

}