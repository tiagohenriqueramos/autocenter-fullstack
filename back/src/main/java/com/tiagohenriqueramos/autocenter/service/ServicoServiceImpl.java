package com.tiagohenriqueramos.autocenter.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tiagohenriqueramos.autocenter.entities.Servico;
import com.tiagohenriqueramos.autocenter.repositories.ServicoRepository;
import com.tiagohenriqueramos.autocenter.service.exceptions.DatabaseException;
import com.tiagohenriqueramos.autocenter.service.exceptions.ResourceNotFoundException;

@Service
public class ServicoServiceImpl implements ServicoService{

	@Autowired
	ServicoRepository servicoRepository;
	
	public List<Servico> listarServicos() {
		List<Servico> listaServicos = servicoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
		return listaServicos;
	}

	@Override
	public Servico buscarPorId(Long id) {
		Optional<Servico> servico = servicoRepository.findById(id);
		return servico.orElseThrow(() -> new ResourceNotFoundException("Sem servico com essa Id informada!"));

	}

	@Override
	public Servico cadastrarServico(Servico servico) {
		return servicoRepository.save(servico);

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
	}

	@Override
	public void deletarServico(Long id) {
		try {
			servicoRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Servico não encotrado!");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possivel excluir um servico agendado");
		}

	}

}
