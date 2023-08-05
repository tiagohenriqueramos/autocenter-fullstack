package br.com.tiagohenriqueramos.autocenterfullstack.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.tiagohenriqueramos.autocenterfullstack.enums.StatusAgendamentos;

public class AgendamentoDTO {
    private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horario;
	
    private ClienteDTO cliente;
    
    private ServicoDTO servico;
    
    private StatusAgendamentos status = StatusAgendamentos.PENDENTE;

    public AgendamentoDTO() {}
   


    public AgendamentoDTO(Long id, LocalDate data, LocalTime horario, ClienteDTO cliente, ServicoDTO servico, StatusAgendamentos status) {
		this.id = id;
		this.data = data;
		this.horario = horario;
		this.cliente = cliente;
		this.servico = servico;
		this.status = status;
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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }



	public ServicoDTO getServico() {
		return servico;
	}

	public void setServico(ServicoDTO servico) {
		this.servico = servico;
	}



	public StatusAgendamentos getStatus() {
		return status;
	}



	public void setStatus(StatusAgendamentos status) {
		this.status = status;
	}
	
	
    
}
