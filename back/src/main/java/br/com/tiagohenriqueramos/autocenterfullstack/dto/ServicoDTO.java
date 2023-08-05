package br.com.tiagohenriqueramos.autocenterfullstack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ServicoDTO {
	private Long id;
	 @JsonInclude(Include.NON_NULL)
	    private String descricaoServico;

	    @JsonInclude(Include.NON_NULL)
	    private Double maoDeObra;

	    @JsonInclude(Include.NON_NULL)
	    private ProdutoDTO produto;
    
    public ServicoDTO() {}
    
	
	
	public ServicoDTO(Long id, String descricaoServico, Double maoDeObra, ProdutoDTO produto) {
		this.id = id;
		this.descricaoServico = descricaoServico;
		this.maoDeObra = maoDeObra;
		this.produto = produto;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
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
	
	@Override
    public String toString() {
        return "ServicoDTO{" +
                "produto=" + produto +
                ", descricaoServico='" + descricaoServico + '\'' +
                ", maoDeObra=" + maoDeObra +
                '}';
    }
}

