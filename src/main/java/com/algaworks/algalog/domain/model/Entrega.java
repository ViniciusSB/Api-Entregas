package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algalog.domain.ValidationGroups;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Cliente cliente;
	
	@Embedded //Anotacao que indica que os campos de destinatario estarao presentes ta tabela Entrega do banco de dados 
	private Destinatario destinatario;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL) //Faz a modificacao em cascata em ocorencias sempre que for alterado, cmo no metodo adicionarOcorrencia
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	
	private BigDecimal taxa;
	
	@Enumerated(EnumType.STRING) //Armazena na coluna o valor em String do Enum
	private StatusEntrega status;
	
	private OffsetDateTime dataPedido;
	
	private OffsetDateTime dataFinalizacao;

	public Ocorrencia adicionarOcorrencia(String descricao) {
		
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);
		
		this.getOcorrencias().add(ocorrencia);
		
		return ocorrencia;
	}

	public void finalizar() {
		
		if (podeSerAlterada()) {
			setStatus(StatusEntrega.FINALIZADA);
			setDataFinalizacao(OffsetDateTime.now());
		} 
		else if (jaFinalizada()) {
			throw new NegocioException("Entrega já finalizada");
		} else {
			throw new NegocioException("Entrega não pode ser finalizada");
		}
	}
	
	public void cancelar() {
		if (podeSerAlterada()) {
			setStatus(StatusEntrega.CANCELADA);
			setDataFinalizacao(OffsetDateTime.now());
		} 
		else if (jaCancelada()) {
			throw new NegocioException("Entrega já cancelada");
		} else {
			throw new NegocioException("Entrega não pode ser cancelada");
		}
	}
	
	public boolean podeSerAlterada() {
		return StatusEntrega.PENDENTE.equals(getStatus());
	}
	
	public boolean jaFinalizada() {
		return StatusEntrega.FINALIZADA.equals(getStatus());
	}
	
	public boolean jaCancelada() {
		return StatusEntrega.CANCELADA.equals(getStatus());
	}
	
}
 