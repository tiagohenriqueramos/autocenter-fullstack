package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Servico;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.ProdutoRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.ServicoRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.service.exceptions.DatabaseException;
import br.com.tiagohenriqueramos.autocenterfullstack.service.exceptions.ResourceNotFoundException;

@Service
public class ServicoServiceImpl implements ServicoService {

	DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final ServicoRepository servicoRepository;
	private final ProdutoRepository produtoRepository;

	@Autowired
	public ServicoServiceImpl(ServicoRepository servicoRepository, ProdutoRepository produtoRepository) {
		this.servicoRepository = servicoRepository;
		this.produtoRepository = produtoRepository;
	}

	public List<Servico> listarServicos() {
		List<Servico> listaServicos = servicoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
		return listaServicos;
	}

	public Servico buscarPorId(Long id) {
		Optional<Servico> servico = servicoRepository.findById(id);
		return servico.orElseThrow(() -> new ResourceNotFoundException("Sem servico com essa Id informada!"));

	}

	public Servico cadastrarServico(Servico servico) {
		return servicoRepository.save(servico);
	}

	public void deletarServico(Long id) {
		try {
			servicoRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Servico não encotrado!");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possivel excluir um servico agendado");
		}

	}

	public Servico editarServico(Long id, Servico novoServico) {
		try {
			Servico servico = servicoRepository.getReferenceById(id);
			atualizarDados(novoServico, servico);
			return servicoRepository.save(servico);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Servico não encontrado!");
		}
	}

	public void atualizarDados(Servico novoServico, Servico servico) {
		servico.setDescricaoServico(novoServico.getDescricaoServico());
		servico.setMaoDeObra(novoServico.getMaoDeObra());
		servico.setProdutoId(novoServico.getProdutoId());
	}

	
}