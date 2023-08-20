package com.tiagohenriqueramos.autocenter.service;

import java.util.List;

import com.tiagohenriqueramos.autocenter.entities.Produto;

public interface ProdutoService {

	List<Produto> listarProdutos();

	Produto buscarPorId(Long id);

	Produto cadastrarProduto(Produto produto);

	Produto editarProduto(Long id, Produto produto);

	void deletarProduto(Long id);


}