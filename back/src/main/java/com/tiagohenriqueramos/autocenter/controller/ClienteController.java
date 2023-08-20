package com.tiagohenriqueramos.autocenter.controller;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiagohenriqueramos.autocenter.entities.Cliente;
import com.tiagohenriqueramos.autocenter.service.ClienteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	ClienteService clienteService;
	
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos() {
		List<Cliente> lista = clienteService.listarClientes();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> encontrarPorId(@PathVariable Long id) {
		Cliente Cliente = clienteService.buscarPorId(id);
		return ResponseEntity.ok().body(Cliente);

	}
	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente Cliente) {
		Cliente = clienteService.cadastrarCliente(Cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).body(Cliente);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> editar(@PathVariable Long id, @RequestBody Cliente Cliente) {
		Cliente = clienteService.editarCliente(id, Cliente);
		return ResponseEntity.ok().body(Cliente);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		clienteService.deletarCliente(id);
			return ResponseEntity.noContent().build();
	}
	
}