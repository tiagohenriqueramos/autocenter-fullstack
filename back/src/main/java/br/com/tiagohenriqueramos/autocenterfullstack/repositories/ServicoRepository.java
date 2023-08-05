package br.com.tiagohenriqueramos.autocenterfullstack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiagohenriqueramos.autocenterfullstack.dto.ServicoDTO;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Servico;


public interface ServicoRepository extends JpaRepository<Servico, Long> {
	
	Optional<Servico> findById(Long id);

	ServicoDTO save(ServicoDTO servicoDTO);

}
