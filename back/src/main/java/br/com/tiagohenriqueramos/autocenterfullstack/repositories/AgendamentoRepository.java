package br.com.tiagohenriqueramos.autocenterfullstack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Agendamento;
import br.com.tiagohenriqueramos.autocenterfullstack.enums.StatusAgendamentos;


public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	List<Agendamento> findByStatus(StatusAgendamentos status);


}
