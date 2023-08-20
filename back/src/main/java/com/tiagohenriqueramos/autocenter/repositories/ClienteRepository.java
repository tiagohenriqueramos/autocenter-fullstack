package com.tiagohenriqueramos.autocenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagohenriqueramos.autocenter.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	

}
