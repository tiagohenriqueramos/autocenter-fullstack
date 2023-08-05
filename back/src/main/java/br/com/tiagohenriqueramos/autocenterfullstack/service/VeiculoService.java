package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.util.List;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Veiculo;

public interface VeiculoService {

	List<Veiculo> listarVeiculos();

	Veiculo buscarPorId(Long id);

	Veiculo cadastrarVeiculo(Veiculo veiculo);

	void deletarVeiculo(Long id);
	
	Veiculo editarVeiculo(Long id, Veiculo veiculo);

	Veiculo salvarVeiculo(Veiculo veiculo, Long clienteId);




}
