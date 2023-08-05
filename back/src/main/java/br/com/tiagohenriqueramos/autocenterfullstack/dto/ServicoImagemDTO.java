package br.com.tiagohenriqueramos.autocenterfullstack.dto;

import org.springframework.web.multipart.MultipartFile;

public class ServicoImagemDTO {
    private Long id;
    private String nomeImagem;
    private String descricaoImagem;
    private MultipartFile arquivo;
    private Long servicoId;

    public ServicoImagemDTO() {}

    public ServicoImagemDTO(Long id, String nomeImagem, String descricaoImagem, MultipartFile arquivo, Long servicoId) {
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

    public MultipartFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(MultipartFile arquivo) {
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
        return "ServicoImagemDTO{" +
                "id=" + id +
                ", nomeImagem='" + nomeImagem + '\'' +
                ", descricaoImagem='" + descricaoImagem + '\'' +
                ", arquivo=" + arquivo +
                ", servicoId=" + servicoId +
                '}';
    }
}
