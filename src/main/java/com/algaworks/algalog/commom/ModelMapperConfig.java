package com.algaworks.algalog.commom;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Declara que esta e uma classe de configuracao de beans 
public class ModelMapperConfig {
	
	@Bean //Inicializa e configura o bean gerenciado pelo Spring permitindo a injecao em outras classes
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
}
