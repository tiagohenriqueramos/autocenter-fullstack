package br.com.tiagohenriqueramos.autocenterfullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
