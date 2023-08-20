package com.tiagohenriqueramos.autocenter.service;

import java.util.List;

import com.tiagohenriqueramos.autocenter.dto.AgendamentoDTO;
import com.tiagohenriqueramos.autocenter.entities.Agendamento;
import com.tiagohenriqueramos.autocenter.enums.StatusAgendamentos;

public interface AgendamentoService {

	List<Agendamento> listarAgendamento();
	
	Agendamento buscarPorId(Long id);
	
    List<Agendamento> buscarAgendamentosPorStatus(StatusAgendamentos status);
    
	AgendamentoDTO cadastrarAgendamento(AgendamentoDTO agendamentoDTO);

	Agendamento editarAgendamento(Long id, Agendamento agendamento);
	
    Agendamento concluirAgendamento(Long agendamento);
	
	void deletarAgendamento(Long id);
	
}
