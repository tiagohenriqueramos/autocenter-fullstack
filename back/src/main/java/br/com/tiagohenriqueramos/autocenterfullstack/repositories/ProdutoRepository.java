package br.com.tiagohenriqueramos.autocenterfullstack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Optional<Produto> findById(Long id);

}
