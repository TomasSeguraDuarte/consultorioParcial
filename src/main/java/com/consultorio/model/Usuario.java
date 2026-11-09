package com.consultorio.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(unique = true)
	private String password;
	
	@Column
	private String nombre;
	
	@ManyToMany
	@JoinTable(
			name = "users_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id"))
	Set<Rol> Rol = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
			name = "pacientes_cita",
			joinColumns = @JoinColumn(name = "paciente_id"),
			inverseJoinColumns = @JoinColumn(name = "cita_id"))
	Set<Cita> Cita = new HashSet<>();
}
