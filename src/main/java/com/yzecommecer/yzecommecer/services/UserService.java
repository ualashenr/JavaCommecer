package com.yzecommecer.yzecommecer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yzecommecer.yzecommecer.dto.UserDTO;
import com.yzecommecer.yzecommecer.entities.Role;
import com.yzecommecer.yzecommecer.entities.User;
import com.yzecommecer.yzecommecer.projections.UserProjection;
import com.yzecommecer.yzecommecer.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserProjection> result = repository.searchUserAndRolesByEmail(username);
		if(result.size() == 0) {
			throw new UsernameNotFoundException("Nome de usuário não encontrado");
		}
		User user = new User();
		user.setEmail(username);
		user.setPassword(result.get(0).getPassword());
		for(UserProjection projection : result) {
			user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}		
		return user;
	}
	
	protected User authenticated() {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			return repository.findByEmail(username).get();
			
		} catch(Exception e) {
			throw new UsernameNotFoundException("Email not found");
		}
	}
	
	@Transactional(readOnly = true)
	public UserDTO getMe() {
		User user = authenticated();
		return new UserDTO(user);
		
	}

}
