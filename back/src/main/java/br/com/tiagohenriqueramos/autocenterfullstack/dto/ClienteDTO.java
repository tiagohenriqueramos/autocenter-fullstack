package br.com.tiagohenriqueramos.autocenterfullstack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ClienteDTO {
	private Long id;
	 @JsonInclude(Include.NON_NULL)
	    private String nome;

	    @JsonInclude(Include.NON_NULL)
	    private String cpf;

	    @JsonInclude(Include.NON_NULL)
	    private String email;

	    @JsonInclude(Include.NON_NULL)
	    private String telefone;

	public ClienteDTO() {
	}

	public ClienteDTO(Long id, String nome, String cpf, String email, String telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
