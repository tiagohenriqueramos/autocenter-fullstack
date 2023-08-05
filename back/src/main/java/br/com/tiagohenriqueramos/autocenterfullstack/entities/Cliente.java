package br.com.tiagohenriqueramos.autocenterfullstack.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String nome;

	@NonNull
	private String cpf;

	@NonNull
	private String email;

	@Size(min = 11)
	@NonNull
	private String telefone;



	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	@JsonIgnore
	private Set<Veiculo> veiculos = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	@JsonIgnore
	private Set<Agendamento> agendamentos = new HashSet<>();

	public Cliente(@NonNull String nome, @NonNull String cpf, @NonNull String email,
			@Size(min = 11) @NonNull String telefone ) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}

}
