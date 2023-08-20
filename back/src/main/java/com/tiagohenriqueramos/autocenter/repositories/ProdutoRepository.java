package com.tiagohenriqueramos.autocenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagohenriqueramos.autocenter.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
