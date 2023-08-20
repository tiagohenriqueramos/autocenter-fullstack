package com.tiagohenriqueramos.autocenter.service;

import java.util.List;

import com.tiagohenriqueramos.autocenter.entities.Veiculo;

public interface VeiculoService {

	List<Veiculo> listarVeiculos();

	Veiculo buscarPorId(Long id);

	Veiculo cadastrarVeiculo(Veiculo veiculo);
	
	Veiculo editarVeiculo(Long id, Veiculo veiculo);

	void deletarVeiculo(Long id);


}
