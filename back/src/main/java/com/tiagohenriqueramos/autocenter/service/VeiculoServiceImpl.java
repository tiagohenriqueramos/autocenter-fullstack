package com.tiagohenriqueramos.autocenter.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tiagohenriqueramos.autocenter.entities.Veiculo;
import com.tiagohenriqueramos.autocenter.repositories.VeiculoRepository;
import com.tiagohenriqueramos.autocenter.service.exceptions.ResourceNotFoundException;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Override
	public List<Veiculo> listarVeiculos() {
		List<Veiculo> lista = veiculoRepository.findAll(Sort.by(Sort.Direction.ASC, "modelo"));
		return lista;
	}

	@Override
	public Veiculo buscarPorId(Long id) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
		return veiculo.orElseThrow(() -> new ResourceNotFoundException("Sem veiculo com essa Id informada!"));
	}

	@Override
	public Veiculo cadastrarVeiculo(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}

	@Override
	public Veiculo editarVeiculo(Long id, Veiculo novoVeiculo) {
		Veiculo veiculo = veiculoRepository.getReferenceById(id);
		atualizarDados(novoVeiculo, veiculo);
		return veiculoRepository.save(veiculo);
	}

	public void atualizarDados(Veiculo novoVeiculo, Veiculo veiculo) {
		veiculo.setMarca(novoVeiculo.getMarca());
		veiculo.setModelo(novoVeiculo.getModelo());
		veiculo.setAno(novoVeiculo.getAno());
	}

	@Override
	public void deletarVeiculo(Long id) {
		try {
			veiculoRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Veiculo não encotrado!");
		}
	}

}
