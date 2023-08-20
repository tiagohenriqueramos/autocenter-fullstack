package com.tiagohenriqueramos.autocenter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagohenriqueramos.autocenter.entities.Agendamento;
import com.tiagohenriqueramos.autocenter.enums.StatusAgendamentos;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	List<Agendamento> findByStatus(StatusAgendamentos status);

	

}
