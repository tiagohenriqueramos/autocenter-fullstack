package com.tiagohenriqueramos.autocenter.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiagohenriqueramos.autocenter.entities.Servico;
import com.tiagohenriqueramos.autocenter.enums.StatusAgendamentos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AgendamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long cliente_id;
	
	private Long veiculo_id;
	
	private Long servico_id;
		
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@NonNull
	private LocalDate data;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@NonNull
	private LocalTime hora;

	@Enumerated(EnumType.STRING)
	private StatusAgendamentos status = StatusAgendamentos.PENDENTE;
	
	
	@ManyToMany
	@JoinTable(name = "tb_agendamento_servico", joinColumns = @JoinColumn(name = "agendamento_id"), inverseJoinColumns = @JoinColumn(name = "servico_id"))
	private Set<Servico> servicos = new HashSet<>();
}