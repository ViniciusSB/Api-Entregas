package com.algaworks.algalog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	//Spring Data JPA fornece a pesquisa em tempo de execucão, para que a consulta seja bem sucedida os metodos devem seguir o padrao do Spring Data JPA
	
	List<Cliente> findByNome(String nome);

	List<Cliente> findByNomeContaining(String nome); //Pesquisa nao exata, como o "like" do sql
	
	Optional<Cliente> findByEmail(String email);
	
}
