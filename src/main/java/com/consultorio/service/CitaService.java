package com.consultorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultorio.model.Cita;
import com.consultorio.repository.CitaRepository;
import com.consultorio.repository.UsuarioRepository;

@Service
public class CitaService {
	@Autowired
	private UsuarioRepository usuariosRepository;
	
	@Autowired
	private CitaRepository citaRepository;
	
	
	public Cita createCita(Cita cita) {
		if(cita.getMotivo_consulta().isBlank() || cita.getNombre().isBlank() || cita.getNum_consultorio()<=0) {
			throw new RuntimeException("Datos de cita invalidos");
		}
		
		return citaRepository.save(cita);
	}
	
	public Cita getCitaById(Long id) {
		Cita cita = citaRepository.findById(id).orElseThrow();
		return cita;
	}
	
	public List<Cita> listCitas(){
		return citaRepository.findAll();
	}
	
	public void deleteCita(Long id) {
		Cita cita = citaRepository.findById(id).orElseThrow();
		citaRepository.delete(cita);
	}
	
	public Cita updateCita(Long id, Cita citaDetails) {
		Cita cita = citaRepository.findById(id).orElseThrow();
		cita.setNombre(citaDetails.getNombre());
		cita.setMotivo_consulta(citaDetails.getMotivo_consulta());
		cita.setNum_consultorio(citaDetails.getNum_consultorio());
		cita.setHora_entrada(citaDetails.getHora_entrada());
		cita.setHora_salida(citaDetails.getHora_salida());
		return citaRepository.save(cita);
	}
}
