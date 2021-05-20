package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@PersistenceContext
	private EntityManager manager; //Realiza operacoes com as entidades relacionadas a ele. Como insert, update, etc
	
	@GetMapping("/clientes") //Mapeia o método listar na chamada de clientes
	public List<Cliente> listar() {
		
		return manager.createQuery("from Cliente", Cliente.class).getResultList();
	}
	
}