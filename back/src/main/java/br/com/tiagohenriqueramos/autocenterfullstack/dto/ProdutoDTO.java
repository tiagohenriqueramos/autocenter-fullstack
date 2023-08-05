package br.com.tiagohenriqueramos.autocenterfullstack.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonInclude(Include.NON_NULL)
    private String nome;

    @JsonInclude(Include.NON_NULL)
    private String descricaoProduto;

    @JsonInclude(Include.NON_NULL)
    private Double preco;


    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String nome, String descricaoProduto, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricaoProduto = descricaoProduto;
        this.preco = preco;
    }

    // Getters e setters

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

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
