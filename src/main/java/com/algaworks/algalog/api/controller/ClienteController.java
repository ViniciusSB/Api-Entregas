package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes") //Mapeia o método listar na chamada de clientes
	public List<Cliente> listar() {
		
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Ricardo");
		cliente1.setTelefone("58 99999-9999");
		cliente1.setEmail("ricardoneves@gmail.com");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Maria");
		cliente2.setTelefone("61 88888-8888");
		cliente2.setEmail("mariajoaquina@gmail.com");
		
		return Arrays.asList(cliente1, cliente2);
	}
	
}