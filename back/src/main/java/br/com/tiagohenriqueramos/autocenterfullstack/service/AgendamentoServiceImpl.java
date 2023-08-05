package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Agendamento;
import br.com.tiagohenriqueramos.autocenterfullstack.enums.StatusAgendamentos;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.AgendamentoRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.service.exceptions.ResourceNotFoundException;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

	DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	public List<Agendamento> findAll() {
		return agendamentoRepository.findAll();
	}

	@Override
	public List<Agendamento> listarAgendamentos() {
		List<Agendamento> listaAgendamentos = agendamentoRepository
				.findAll((Sort.by(Sort.Direction.ASC, "data").and((Sort.by(Sort.Direction.ASC, "horario")))));
		return listaAgendamentos;
	}

	public Agendamento cadastrarAgendamento(Agendamento agendamento) {
		return agendamentoRepository.save(agendamento);
	}

	@Override
	public Agendamento buscarPorId(Long id) {
		Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
		return agendamento.orElseThrow(() -> new ResourceNotFoundException("Sem agendamento com essa Id informada!"));
	}

	

	@Override
	public void deletarAgendamento(Long id) {

		try {
			agendamentoRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Agendamento não encotrado!");
		}
	}

	@Override
	public Agendamento editarAgendamento(Long id, Agendamento novoAgendamento) {

		try {
			Agendamento agendamento = agendamentoRepository.getReferenceById(id);
			atualizarDados(novoAgendamento, agendamento);
			return agendamentoRepository.save(agendamento);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Agendamento não encontrado!");
		}
	}

	public void atualizarDados(Agendamento novoAgendamento, Agendamento agendamento) {
		agendamento.setCliente_Id(novoAgendamento.getCliente_Id());
		agendamento.setData(novoAgendamento.getData());
		agendamento.setHorario(novoAgendamento.getHorario());
	}
	

	 @Override
	    public List<String> obterHorariosEDatasExistentes() {
	        List<Agendamento> agendamentos = agendamentoRepository.findAll();
	        return agendamentos.stream()
	                .map(agendamento -> agendamento.getData().toString() + " " + agendamento.getHorario().toString())
	                .collect(Collectors.toList());
	    }


	    @Override
	    public Agendamento concluirAgendamento(Long agendamentoId) {
	        Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElse(null);
	        if (agendamento != null) {
	            agendamento.setStatus(StatusAgendamentos.CONCLUIDO);
	            agendamentoRepository.save(agendamento);
	        }
	        return agendamento;
	    }
	    
	    public List<Agendamento> buscarAgendamentosPorStatus(StatusAgendamentos status) {
	        return agendamentoRepository.findByStatus(status);
	    }

		@Override
		public Agendamento encontrarPorId(Long id) {
			Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
			return agendamento.orElseThrow(() -> new ResourceNotFoundException("Sem agendamento com essa Id informada!"));
		}

}
