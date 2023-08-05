package br.com.tiagohenriqueramos.autocenterfullstack.entities;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Builder
@Entity
@Table(name = "servico_imagens")

public class ServicoImagem {

    

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String nomeImagem;
	
    private String descricaoImagem;
    
    private byte[] arquivo;    
    

    @NonNull
    @JoinColumn(name="servico_id")
    private Long servicoId;

    public ServicoImagem() {}

	public ServicoImagem(Long id, String nomeImagem, String descricaoImagem, byte[] arquivo, 
			@NonNull Long servicoId) {
		super();
		this.id = id;
		this.nomeImagem = nomeImagem;
		this.descricaoImagem = descricaoImagem;
		this.arquivo = arquivo;
		this.servicoId = servicoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public String getDescricaoImagem() {
		return descricaoImagem;
	}

	public void setDescricaoImagem(String descricaoImagem) {
		this.descricaoImagem = descricaoImagem;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}


	public Long getServicoId() {
		return servicoId;
	}

	public void setServicoId(Long servicoId) {
		this.servicoId = servicoId;
	}

	@Override
	public String toString() {
		return "ServicoImagem [id=" + id + ", nomeImagem=" + nomeImagem + ", descricaoImagem=" + descricaoImagem
				+ ", arquivo=" + Arrays.toString(arquivo)  + ", servicoId=" + servicoId + "]";
	}


}