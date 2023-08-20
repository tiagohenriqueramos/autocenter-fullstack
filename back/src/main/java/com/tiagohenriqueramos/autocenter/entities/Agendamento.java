package com.tiagohenriqueramos.autocenter.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity
@Table(name = "tb_agendamento")
public class Agendamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NonNull
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne	
	@NonNull
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;
	
	@ManyToOne	
	@NonNull
	@JoinColumn(name = "servico_id")
	private Servico servico;
		
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@NonNull
	private LocalDate data;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@NonNull
	private LocalTime hora;

	@Enumerated(EnumType.STRING)
	private StatusAgendamentos status = StatusAgendamentos.PENDENTE;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_agendamento_servico", joinColumns = @JoinColumn(name = "agendamento_id"), inverseJoinColumns = @JoinColumn(name = "servico_id"))
	private Set<Servico> servicos = new HashSet<>();
}
