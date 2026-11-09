package com.consultorio.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Cita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String motivo_consulta;
	
	@Column(nullable = false)
	private long num_consultorio;
	
	@Column(nullable = false)
	private LocalTime hora_entrada;
	
	@Column(nullable = false)
	private LocalTime hora_salida;
	
	@Column(nullable = false)
	private String nombre;
}
