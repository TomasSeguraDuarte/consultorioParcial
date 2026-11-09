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
	

	@Column
	private String motivo_consulta;
	
	@Column
	private long num_consultorio;
	
	@Column 
	private LocalTime hora_entrada;
	
	@Column
	private LocalTime hora_salida;
}
