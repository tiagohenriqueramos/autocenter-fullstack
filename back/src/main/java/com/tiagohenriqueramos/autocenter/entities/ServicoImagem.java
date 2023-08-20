package com.tiagohenriqueramos.autocenter.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "servico_imagens")
public class ServicoImagem {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NonNull
	private String nomeImagem;
	
	@NonNull
    private String descricaoImagem;
    
    private byte[] arquivo;    
    
    @NonNull
    @JoinColumn(name="cliente_id")
    private Long cliente_id;
    
    
}
