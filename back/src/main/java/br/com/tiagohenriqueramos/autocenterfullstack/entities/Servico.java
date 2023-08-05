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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@AllArgsConstructor

@RequiredArgsConstructor
@Builder

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

	
    @NonNull
    @JoinColumn(name = "produto_id")
    private Long produtoId;
      
    @Transient
    private Produto produto;
    
    
   
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "tb_servico_produto", joinColumns = {
			@JoinColumn(name = "servico_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "produto_id", referencedColumnName = "id") })
	private Set<Produto> produtos = new HashSet<>();

	@JsonIgnore
	@ManyToMany(mappedBy = "servicos", cascade = CascadeType.MERGE)
	private Set<Agendamento> agendamentos = new HashSet<>();
	
	public Servico() {
		
	}
	
	public Servico(Long id,  String descricaoServico,  Double maoDeObra,  Long produtoId) {
		this.id = id;
		this.descricaoServico = descricaoServico;
		this.maoDeObra = maoDeObra;
		this.produtoId = produtoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public Double getMaoDeObra() {
		return maoDeObra;
	}

	public void setMaoDeObra(Double maoDeObra) {
		this.maoDeObra = maoDeObra;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	
	
	
	@Override
    public String toString() {
        return "Servico{" +
                "produtoId=" + produtoId +
                ", descricaoServico='" + descricaoServico + '\'' +
                ", maoDeObra=" + maoDeObra +
                '}';
    }

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	
	
}
