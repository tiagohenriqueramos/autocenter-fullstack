package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.util.List;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Servico;

public interface ServicoService {

	List<Servico> listarServicos();

	Servico buscarPorId(Long id);

	Servico cadastrarServico(Servico servico);

	void deletarServico(Long id);
	
	Servico editarServico(Long id, Servico servico);



	




}
