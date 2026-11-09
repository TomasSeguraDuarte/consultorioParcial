package com.consultorio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean PasswordEncoder PasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests(auth -> auth
					.requestMatchers("/403").permitAll()
					.requestMatchers("/citas").permitAll()
					.requestMatchers("/citas/crear/**").hasAnyAuthority("ADMIN")
					.requestMatchers("/citas/edit/**").hasAnyAuthority("ADMIN","PACIENTE")
					.requestMatchers("/users/consultar").hasAnyAuthority("ADMIN","MEDICO")
					.anyRequest().authenticated()
					)
					.formLogin(form -> form .defaultSuccessUrl("/citas.html").permitAll())
					.logout(form -> form.permitAll())
					.exceptionHandling(e -> e.accessDeniedPage("/citas/403"));
			return http.build();
		}
	}