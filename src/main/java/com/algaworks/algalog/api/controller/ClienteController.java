package com.algaworks.algalog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor //gera um construtor que contem todas as propriedades da classe
@RestController
public class ClienteController {
	
//	@Autowired //Srping Data JPA fornece a implementacao da interface permitindo a utilizacao dos metodos proprios e herdados
	private ClienteRepository clienteRepository;
	
	/*
	public ClienteController(ClienteRepository clienteRepository) { //Injeta o cliente repository atraves do construtor da classe
		super();
		this.clienteRepository = clienteRepository;
	} */

	@GetMapping("/clientes") //Mapeia o método listar na chamada de clientes
	public List<Cliente> listar() {
		
		return clienteRepository.findByNomeContaining("a");
	}
	
}