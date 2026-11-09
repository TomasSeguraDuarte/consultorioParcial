package com.consultorio.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consultorio.model.Rol;
import com.consultorio.model.Usuario;
import com.consultorio.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario u = usuarioRepository.findByUsername(username);
		if (u == null) throw new UsernameNotFoundException("No existe");

		return User.withUsername(u.getUsername())
				.password(u.getPassword())            
				.authorities(u.getRol().stream()
						.map(Rol::getRol)    
						.toArray(String[]::new))
				.build();
	}

}
