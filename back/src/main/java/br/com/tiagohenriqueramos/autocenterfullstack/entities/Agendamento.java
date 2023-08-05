package br.com.tiagohenriqueramos.autocenterfullstack.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tiagohenriqueramos.autocenterfullstack.enums.StatusAgendamentos;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_agendamento")
public class Agendamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@NonNull
	private LocalDate data;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@NonNull
	private LocalTime horario;
	
	@NonNull
	@JoinColumn(name = "cliente_id")
	private Long cliente_Id;

	@NonNull
	@JoinColumn(name = "servico_id")
	private Long servicoId;
	
	
	private Cliente cliente;
	
	
	private Servico servico;
	
	
	private Produto produto;
	
	
    
    @Enumerated(EnumType.STRING)
    private StatusAgendamentos status = StatusAgendamentos.PENDENTE;
    
    
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "tb_agendamento_servico", joinColumns = {
			@JoinColumn(name = "agendamento_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "servico_id", referencedColumnName = "id") })
	private Set<Servico> servicos = new HashSet<>();

	
	public Agendamento() {}
	
		public Agendamento(Long id, @NonNull LocalDate data, @NonNull LocalTime horario, @NonNull Long cliente_Id,
			@NonNull Long servicoId, Cliente cliente, Servico servico, Produto produto, StatusAgendamentos status) {
		super();
		this.id = id;
		this.data = data;
		this.horario = horario;
		this.cliente_Id = cliente_Id;
		this.servicoId = servicoId;
		this.cliente = cliente;
		this.servico = servico;
		this.produto = produto;
		this.status = status;
	}
		
		
	@Override
	public String toString() {
		return "Agendamento [id=" + id + ", data=" + data + ", horario=" + horario + ", clienteId=" + cliente_Id
				+ ", servicoId=" + servicoId +  "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Long getCliente_Id() {
		return cliente_Id;
	}

	public void setCliente_Id(Long cliente_Id) {
		this.cliente_Id = cliente_Id;
	}

	public Long getServicoId() {
		return servicoId;
	}

	public void setServicoId(Long servicoId) {
		this.servicoId = servicoId;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public StatusAgendamentos getStatus() {
		return status;
	}

	public void setStatus(StatusAgendamentos status) {
		this.status = status;
	}
}
