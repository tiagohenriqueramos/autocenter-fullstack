package br.com.tiagohenriqueramos.autocenterfullstack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.ServicoImagem;


@Repository
public interface ServicoImagemRepository extends JpaRepository<ServicoImagem, Long>{
    
	public Optional<ServicoImagem> findById(Long id);
	
	



	
}