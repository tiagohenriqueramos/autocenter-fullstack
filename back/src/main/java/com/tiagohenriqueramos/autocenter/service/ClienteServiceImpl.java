package com.tiagohenriqueramos.autocenter.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tiagohenriqueramos.autocenter.entities.Agendamento;
import com.tiagohenriqueramos.autocenter.entities.Cliente;
import com.tiagohenriqueramos.autocenter.entities.Veiculo;
import com.tiagohenriqueramos.autocenter.repositories.AgendamentoRepository;
import com.tiagohenriqueramos.autocenter.repositories.ClienteRepository;
import com.tiagohenriqueramos.autocenter.repositories.VeiculoRepository;
import com.tiagohenriqueramos.autocenter.service.exceptions.ResourceNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Override
	public List<Cliente> listarClientes() {
		List<Cliente> lista = clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		return lista;
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ResourceNotFoundException("Sem cliente com essa id informada!"));
	}

	@Override
	public Cliente cadastrarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente editarCliente(Long id, Cliente novoCliente) {
		try {
			Cliente cliente = clienteRepository.getReferenceById(id);
			atualizarDados(novoCliente, cliente);
			return clienteRepository.save(cliente);
		} catch (EntityNotFoundException e) {
			throw new ResourceClosedException("Cliente não encontrado!");
		}

	}

	public void atualizarDados(Cliente novoCliente, Cliente cliente) {
		cliente.setNome(novoCliente.getNome());
		cliente.setCpf(novoCliente.getCpf());
		cliente.setEmail(novoCliente.getEmail());
		cliente.setTelefone(novoCliente.getTelefone());
	}

	@Override
	public void deletarCliente(Long id) {
	    try {
	        Agendamento agendamento = agendamentoRepository.findById(id).orElse(null);
	        Veiculo veiculo = veiculoRepository.findById(id).orElse(null);

	        if (agendamento == null && veiculo == null) {
	            clienteRepository.deleteById(id);
	        } else {
	            agendamentoRepository.deleteById(id);
	            veiculoRepository.deleteById(id);
	            clienteRepository.deleteById(id);
	        }
	    } catch (EntityNotFoundException e) {
	        throw new ResourceNotFoundException("Cliente não encontrado!");
	    }
	}

}
