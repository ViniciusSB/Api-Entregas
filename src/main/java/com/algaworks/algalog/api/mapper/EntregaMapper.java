package com.algaworks.algalog.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@Component //Classe gerenciada pelo Spring
@AllArgsConstructor
public class EntregaMapper { //Classe responsavel por fazer a convercao de Entrega p/ EntregaModel utilizando o ModelMapper

	private ModelMapper modelMapper;
	
	public EntregaModel toModel(Entrega entrega) {
		
		return modelMapper.map(entrega, EntregaModel.class);
	}
	
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
		return entregas.stream() //Convertendo uma stream de entregas para uma stram de EntregaModel
				.map(this::toModel) //Convertendo com Method Reference
				//.map(entrega -> toModel(entrega)) //Convertendo com expressao lambda
				.collect(Collectors.toList()); //Convertendo de stream para list
	}
	
	public Entrega toEntity(EntregaInput entregaInputModel) {
		
		return modelMapper.map(entregaInputModel, Entrega.class);
	}

}
