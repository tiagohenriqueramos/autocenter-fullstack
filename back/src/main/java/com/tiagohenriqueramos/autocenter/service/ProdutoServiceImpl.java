package com.tiagohenriqueramos.autocenter.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tiagohenriqueramos.autocenter.entities.Produto;
import com.tiagohenriqueramos.autocenter.repositories.ProdutoRepository;
import com.tiagohenriqueramos.autocenter.service.exceptions.ResourceNotFoundException;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> listarProdutos() {
		List<Produto> lista = produtoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		return lista;
	}

	@Override
	public Produto buscarPorId(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ResourceNotFoundException("Sem produto com essa Id informada!"));
	}

	@Override
	public Produto cadastrarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Produto editarProduto(Long id, Produto novoProduto) {
		
		try {
			Produto produto = produtoRepository.getReferenceById(id);
			atualizarDados(novoProduto, produto);
			return produtoRepository.save(produto);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Produto não encontrado");
		}
	}
	
	public void atualizarDados(Produto novoProduto, Produto produto) {
		produto.setNome(novoProduto.getNome());
		produto.setDescricaoProduto(novoProduto.getDescricaoProduto());
		produto.setPreco(novoProduto.getPreco());
	}

	@Override
	public void deletarProduto(Long id) {
		
		try {
			produtoRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Produto não encotrado!");
		}
	}

}
