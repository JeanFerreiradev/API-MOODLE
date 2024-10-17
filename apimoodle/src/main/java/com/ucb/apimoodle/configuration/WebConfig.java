package com.ucb.apimoodle.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//CONFIGURA CORS PARA PERMITIR SOLICITAÇÕES DE DETERMINADO IP
@Configuration
@CrossOrigin
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://127.0.0.1:5500", "http://127.0.0.1:5501")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.allowCredentials(true)
				.maxAge(3600);	
	}
}
