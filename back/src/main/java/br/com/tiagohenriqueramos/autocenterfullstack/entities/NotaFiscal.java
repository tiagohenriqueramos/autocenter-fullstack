package br.com.tiagohenriqueramos.autocenterfullstack.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@NoArgsConstructor

@AllArgsConstructor

@Entity
@Table(name = "nota_fiscal")
@XmlRootElement(name = "notaFiscal")
public class NotaFiscal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeEmitente;
	private String cnpjEmitente;
	private String inscricaoEstadualEmitente;
	private String enderecoEmitente;
	private String nomeDestinatario;
	private String cpfCnpjDestinatario;
	private String enderecoDestinatario;
	private String descricaoProduto;
	private double valorProduto;
	private double maoDeObra;
	private double valorTotal;
	private String cfop;
	private String cst;
	private double aliquota;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@NonNull	
	private LocalDate dataEmissao;
	private Long numeroNotaFiscal;
	private String formaPagamento;
	private String informacoesAdicionais;

	private Long AgendamentoId;
	private Long ClienteId;
	private Long ServicoId;
	@Transient
	private Agendamento agendamento;

	@Transient
	private Cliente cliente;

	@Transient
	private Servico servico;
	@Transient
	private Produto produto;
	
	



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEmitente() {
		return nomeEmitente;
	}

	public void setNomeEmitente(String nomeEmitente) {
		this.nomeEmitente = nomeEmitente;
	}

	public String getCnpjEmitente() {
		return cnpjEmitente;
	}

	public void setCnpjEmitente(String cnpjEmitente) {
		this.cnpjEmitente = cnpjEmitente;
	}

	public String getInscricaoEstadualEmitente() {
		return inscricaoEstadualEmitente;
	}

	public void setInscricaoEstadualEmitente(String inscricaoEstadualEmitente) {
		this.inscricaoEstadualEmitente = inscricaoEstadualEmitente;
	}

	public String getEnderecoEmitente() {
		return enderecoEmitente;
	}

	public void setEnderecoEmitente(String enderecoEmitente) {
		this.enderecoEmitente = enderecoEmitente;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public String getCpfCnpjDestinatario() {
		return cpfCnpjDestinatario;
	}

	public void setCpfCnpjDestinatario(String cpfCnpjDestinatario) {
		this.cpfCnpjDestinatario = cpfCnpjDestinatario;
	}

	public String getEnderecoDestinatario() {
		return enderecoDestinatario;
	}

	public void setEnderecoDestinatario(String enderecoDestinatario) {
		this.enderecoDestinatario = enderecoDestinatario;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public double getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public double getAliquota() {
		return aliquota;
	}

	public void setAliquota(double aliquota) {
		this.aliquota = aliquota;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Long getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(Long numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}

	public Long getAgendamentoId() {
		return AgendamentoId;
	}

	public void setAgendamentoId(Long agendamentoId) {
		AgendamentoId = agendamentoId;
	}

	public Long getClienteId() {
		return ClienteId;
	}

	public void setClienteId(Long clienteId) {
		ClienteId = clienteId;
	}

	public Long getServicoId() {
		return ServicoId;
	}

	public void setServicoId(Long servicoId) {
		ServicoId = servicoId;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
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



	public double getMaoDeObra() {
		return maoDeObra;
	}



	public void setMaoDeObra(double maoDeObra) {
		this.maoDeObra = maoDeObra;
	}



	
}
