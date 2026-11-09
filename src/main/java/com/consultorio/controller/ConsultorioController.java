package com.consultorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.consultorio.service.CitaService;
import com.consultorio.service.UsuarioService;

@Controller
@RequestMapping("/consultorio")
public class ConsultorioController {

		@Autowired
		private UsuarioService usuarioService;
		
		@Autowired
		private CitaService citaService;
		
		@GetMapping("/consultorio")
		public String goToConsultorio() {
			return "redirect/consultorio.html";
		}
		
		@GetMapping("/error")
		public String error() {
			return "redirect/404.html";
		}
}
