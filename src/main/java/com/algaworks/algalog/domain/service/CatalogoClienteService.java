package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@Service //Componente que representa os servicos que serao executados (regras de negócio) 
@AllArgsConstructor
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;
	
	@Transactional //Caso ocorra algum erro na transação o processo por inteiro será descartado, evitando erros no banco de dados
	public Cliente salvar (Cliente cliente) {
		
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream().anyMatch(ClienteExistente -> !ClienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
		} else {
			return clienteRepository.save(cliente);			
		}
	}
	
	@Transactional
	public void exluir (Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}
