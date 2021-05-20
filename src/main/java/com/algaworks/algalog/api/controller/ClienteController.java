package com.algaworks.algalog.api.controller;

import java.lang.ref.Cleaner.Cleanable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor //gera um construtor que contem todas as propriedades da classe
@RestController
@RequestMapping("/clientes") //Mapeando /clientes para a classe
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	
	@GetMapping //Mapeia o método listar na chamada de clientes
	public List<Cliente> listar() {
		
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}") //PathVariable: relaciona a variavel do metodo com a variavel do getMapping
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) { //Permite manipular o codigo de status da resposta, entre outros
		
		//return clienteRepository.findById(clienteId).map(cliente -> ResponseEntity.ok(cliente)).orElse(ResponseEntity.notFound().build());
		return clienteRepository.findById(clienteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
		/*
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		*/
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) { //RequestBody: vincula o parametro do metodo com o corpo da requisicao
		
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		} else {
			cliente.setId(clienteId);
			cliente = clienteRepository.save(cliente);
			return ResponseEntity.ok(cliente);
		}
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		} else {
			clienteRepository.deleteById(clienteId);
			return ResponseEntity.noContent().build();
		}
	}
	
}