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

import com.tiagohenriqueramos.autocenter.entities.Produto;
import com.tiagohenriqueramos.autocenter.service.ProdutoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos() {
		List<Produto> lista = produtoService.listarProdutos();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> encontrarPorId(@PathVariable Long id) {
		Produto produto = produtoService.buscarPorId(id);
		return ResponseEntity.ok().body(produto);

	}

	@PostMapping
	public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
		produto = produtoService.cadastrarProduto(produto);
		URI uri = ServletUriComponentsBuilder.fromPath("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> editar(@PathVariable Long id, @RequestBody Produto produto) {
		produto = produtoService.editarProduto(id, produto);
		return ResponseEntity.ok().body(produto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		produtoService.deletarProduto(id);
		return ResponseEntity.noContent().build();
	}
}
