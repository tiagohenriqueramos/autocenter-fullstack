package com.tiagohenriqueramos.autocenter.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tiagohenriqueramos.autocenter.entities.ServicoImagem;

public interface ServicoImagemService {

	List<ServicoImagem> listarTodos();

	List<ServicoImagem> encontrarPorServico(Long id);

	ServicoImagem cadastrar(ServicoImagem servicoImagem, MultipartFile file);

	void deletarImagem(Long id);

}
