package com.consultorio.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.consultorio.model.Cita;
import com.consultorio.model.Rol;
import com.consultorio.model.Usuario;
import com.consultorio.repository.CitaRepository;
import com.consultorio.repository.RolRepository;
import com.consultorio.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private CitaRepository citaRepository;
	
	
	public Usuario createUsuario(Usuario usuario) {
		if(usuarioRepository.existsByUsername(usuario.getUsername())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe");
		}
		if(usuario.getUsername() == null || usuario.getUsername().isBlank()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario no puede ser vacio");
		}
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}
	
	public Usuario getUsuarioById(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow();
		return usuario;
				
	}
	
	public List<Usuario> listUsuarios(){ 
		return usuarioRepository.findAll();
	
	}
	
	public Usuario updateUsuario(Long id, Usuario usuarioDetails, String rol, List<Cita> citas) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow();
		
		Rol role = rolRepository.findByRol(rol);
		if(role == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol inválido");
		}
		
		usuario.getRol().clear();
		usuario.getRol().add(role);
		
		return usuarioRepository.save(usuario);
	}
	
	public void asignarCita(Long usuarioId, Long citaId) {
		
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow();
		
		Cita cita = citaRepository.findById(citaId)
				.orElseThrow();
		Set<Cita> citas = usuario.getCita();
		
		citas.add(cita);
		usuarioRepository.save(usuario);
	}
}
