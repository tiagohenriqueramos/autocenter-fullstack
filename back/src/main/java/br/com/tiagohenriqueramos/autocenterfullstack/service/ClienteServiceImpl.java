package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Cliente;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.ClienteRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.service.exceptions.ResourceNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

	DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listarClientes() {
		List<Cliente> listaClientes = clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		return listaClientes;
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ResourceNotFoundException("Sem cliente com essa Id informada!"));

	}

	@Override
	public Cliente cadastrarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void deletarCliente(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Cliente não encotrado!");
		}

	}

	@Override
	public Cliente editarCliente(Long id, Cliente novoCliente) {

		try {
			Cliente cliente	 = clienteRepository.getReferenceById(id);
			atualizarDados(novoCliente, cliente);
			return clienteRepository.save(cliente);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Cliente não encontrado!");
		}
	}

	public void atualizarDados(Cliente novoCliente, Cliente cliente) {
		cliente.setNome(novoCliente.getNome());
		cliente.setCpf(novoCliente.getCpf());
		cliente.setEmail(novoCliente.getEmail());
		cliente.setTelefone(novoCliente.getTelefone());

	}


}
