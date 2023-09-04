package com.yzecommecer.yzecommecer.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.yzecommecer.yzecommecer.entities.User;

public class UserRegisterDTO{

	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	private LocalDate birthDate;
	
	public UserRegisterDTO(){
	}

	public UserRegisterDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		phone = entity.getPhone();
		birthDate = entity.getBirthDate();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getPassword() {
		return password;
	}
	
	
	
}
