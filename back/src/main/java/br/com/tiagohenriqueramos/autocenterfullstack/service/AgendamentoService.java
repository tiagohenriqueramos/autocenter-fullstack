package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.util.List;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Agendamento;
import br.com.tiagohenriqueramos.autocenterfullstack.enums.StatusAgendamentos;

public interface AgendamentoService {

	List<Agendamento> listarAgendamentos();

	Agendamento buscarPorId(Long id);
	
	Agendamento encontrarPorId(Long id);


	void deletarAgendamento(Long id);

	Agendamento editarAgendamento(Long id, Agendamento agendamento);

	Agendamento cadastrarAgendamento(Agendamento agendamento);

	List<String> obterHorariosEDatasExistentes();
	
    Agendamento concluirAgendamento(Long agendamentoId);
    
     List<Agendamento> buscarAgendamentosPorStatus(StatusAgendamentos status);


}
