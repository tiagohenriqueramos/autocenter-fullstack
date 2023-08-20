package com.tiagohenriqueramos.autocenter.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tiagohenriqueramos.autocenter.dto.AgendamentoDTO;
import com.tiagohenriqueramos.autocenter.entities.Agendamento;
import com.tiagohenriqueramos.autocenter.entities.Cliente;
import com.tiagohenriqueramos.autocenter.entities.Servico;
import com.tiagohenriqueramos.autocenter.entities.Veiculo;
import com.tiagohenriqueramos.autocenter.enums.StatusAgendamentos;
import com.tiagohenriqueramos.autocenter.repositories.AgendamentoRepository;
import com.tiagohenriqueramos.autocenter.service.exceptions.ResourceNotFoundException;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

	@Autowired
	AgendamentoRepository agendamentoRepository;

	@Autowired
	//SmsService smsService;

	@Override
	public List<Agendamento> listarAgendamento() {
		List<Agendamento> listaAgendamentos = agendamentoRepository
				.findAll((Sort.by(Sort.Direction.ASC, "data").and((Sort.by(Sort.Direction.ASC, "hora")))));
		return listaAgendamentos;
	}

	@Override
	public Agendamento buscarPorId(Long id) {
		Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
		return agendamento.orElseThrow(() -> new ResourceNotFoundException("Sem agendamento com essa Id informada!"));
	}

	@Override
	public List<Agendamento> buscarAgendamentosPorStatus(StatusAgendamentos status) {
		return agendamentoRepository.findByStatus(status);
	}

	@Override
	public AgendamentoDTO cadastrarAgendamento(AgendamentoDTO agendamentoDTO) {
		Agendamento agendamento = new Agendamento();

		Cliente cliente = new Cliente();
		cliente.setId(agendamentoDTO.getCliente_id());
		agendamento.setCliente(cliente);

		Veiculo veiculo = new Veiculo();
		veiculo.setId(agendamentoDTO.getVeiculo_id());
		agendamento.setVeiculo(veiculo);
		
		Servico servico = new Servico();
		servico.setId(agendamentoDTO.getServico_id());
		agendamento.setServico(servico);

		agendamento.setData(agendamentoDTO.getData());
		agendamento.setHora(agendamentoDTO.getHora());

		Agendamento agendamentoPersistido = agendamentoRepository.save(agendamento);

		AgendamentoDTO novoAgendamentoDTO = new AgendamentoDTO();
		novoAgendamentoDTO.setId(agendamentoPersistido.getId());
		novoAgendamentoDTO.setCliente_id(agendamentoPersistido.getCliente().getId());
		novoAgendamentoDTO.setServico_id(agendamentoPersistido.getVeiculo().getId());
		novoAgendamentoDTO.setServico_id(agendamentoPersistido.getServico().getId());
		novoAgendamentoDTO.setData(agendamentoPersistido.getData());
		novoAgendamentoDTO.setHora(agendamentoPersistido.getHora());

		return novoAgendamentoDTO;
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
		agendamento.getCliente().setId(novoAgendamento.getCliente().getId());
		agendamento.getVeiculo().setId(novoAgendamento.getVeiculo().getId());
		agendamento.getServico().setId(novoAgendamento.getServico().getId());
		agendamento.setData(novoAgendamento.getData());
		agendamento.setHora(novoAgendamento.getHora());
		agendamento.setStatus(novoAgendamento.getStatus());
	}

	@Override
	public Agendamento concluirAgendamento(Long agendamento) {
		Agendamento agendamentoConcluido = agendamentoRepository.findById(agendamento).orElse(null);
		if (agendamentoConcluido != null) {
			agendamentoConcluido.setStatus(StatusAgendamentos.CONCLUIDO);
			agendamentoRepository.save(agendamentoConcluido);

			enviarSMSParaCliente(agendamentoConcluido);

		}
		return agendamentoConcluido;
	}

    private void enviarSMSParaCliente(Agendamento agendamento) { // Accept Agendamento parameter
        String numeroTelefone = agendamento.getCliente().getTelefone();
        String mensagem = "Seu agendamento foi concluído. Obrigado por escolher nossos serviços!";
     //   smsService.sendSms(numeroTelefone, mensagem);
    }

	@Override
	public void deletarAgendamento(Long id) {

		try {
			agendamentoRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Agendamento não encotrado!");
		}
	}

}
