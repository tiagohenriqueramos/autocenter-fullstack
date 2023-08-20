package com.tiagohenriqueramos.autocenter.service;

import java.util.List;

import com.tiagohenriqueramos.autocenter.entities.Servico;

public interface ServicoService {

	List<Servico> listarServicos();

	Servico buscarPorId(Long id);

	Servico cadastrarServico(Servico servico);

	Servico editarServico(Long id, Servico servico);
	
	void deletarServico(Long id);


}
