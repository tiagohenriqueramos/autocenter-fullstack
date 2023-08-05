package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Cliente;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Veiculo;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.ClienteRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.VeiculoRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.service.exceptions.ResourceNotFoundException;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private VeiculoRepository veiculoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Veiculo> listarVeiculos() {
		List<Veiculo> listaVeiculos = veiculoRepository.findAll(Sort.by(Sort.Direction.ASC, "modelo"));
		return listaVeiculos;
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
	public void deletarVeiculo(Long id) {
		try {
			veiculoRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Agendamento não encotrado!");
		}

	}
    public Veiculo salvarVeiculo(Veiculo veiculo, Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        veiculo.setCliente_Id(cliente.getId());
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

}
