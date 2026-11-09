package com.consultorio.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.consultorio.model.Rol;
import com.consultorio.model.Usuario;
import com.consultorio.repository.RolRepository;
import com.consultorio.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) {
		for (String nombre : List.of("ADMIN", "PACIENTE", "MEDICO")) {
			if (rolRepository.findByRol(nombre) == null) {
				Rol r = new Rol();
				r.setRol(nombre);
				rolRepository.save(r);
			}
		}
		if (usuarioRepository.findByUsername("admin") == null) {
			Usuario admin= new Usuario();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("123"));
			admin.getRol().add(rolRepository.findByRol("ADMIN"));
			usuarioRepository.save(admin);
		}
	}
}
