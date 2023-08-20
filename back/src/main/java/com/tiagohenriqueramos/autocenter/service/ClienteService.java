package com.tiagohenriqueramos.autocenter.service;

import java.util.List;

import com.tiagohenriqueramos.autocenter.entities.Cliente;

public interface ClienteService {

	List<Cliente> listarClientes();
	
	Cliente buscarPorId(Long id);

	Cliente cadastrarCliente(Cliente cliente);

	Cliente editarCliente(Long id, Cliente cliente);
	
	void deletarCliente(Long id);
}
