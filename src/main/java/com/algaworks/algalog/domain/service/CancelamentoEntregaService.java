package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CancelamentoEntregaService {

	private BuscaEntregaService buscaEntregaService;
	private EntregaRepository entregaRepository;
	
	public void cancelar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		entrega.cancelar();
		
		entregaRepository.save(entrega);
	}
	
}
