package com.tiagohenriqueramos.autocenter.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "tb_servico")
public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String descricaoServico;

	@NonNull
	private Double maoDeObra;
	
	@ManyToOne
	@NonNull
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "tb_agendamento_servico",
	joinColumns = @JoinColumn(name = "servico_id"),
	inverseJoinColumns = @JoinColumn(name = "agendamento_id"))
	private Set<Agendamento> agendamentos = new HashSet<>();
	
}
