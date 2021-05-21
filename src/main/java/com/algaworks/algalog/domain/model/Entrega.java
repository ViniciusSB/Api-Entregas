package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	
	private BigDecimal taxa;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING) //Armazena na coluna o valor em String do Enum
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataFinalizacao;
}
 