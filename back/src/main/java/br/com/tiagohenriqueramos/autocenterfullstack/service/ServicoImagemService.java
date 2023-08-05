package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.ServicoImagem;

public interface ServicoImagemService {


	void deletarImagem(Long id);

	
	List<ServicoImagem> buscarPorServico(Long id);

	List<ServicoImagem> listarTodos();
	

	ServicoImagem cadastrarServico(ServicoImagem servicoImagem, MultipartFile file);
	



}
