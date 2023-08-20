package com.tiagohenriqueramos.autocenter.controller;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiagohenriqueramos.autocenter.entities.ServicoImagem;
import com.tiagohenriqueramos.autocenter.service.ServicoImagemService;

@RestController
@RequestMapping("/servicos/imagem")
@CrossOrigin(origins = "*")
@MultipartConfig
public class ServicoImagemController implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@Autowired
	ServicoImagemService servicoImagensService;
	
	@GetMapping
	public List<ServicoImagem> buscarTodos() {
		return servicoImagensService.listarTodos();
	}

	@GetMapping("/{id}")
	public List<ServicoImagem> encontrarPorServico(@PathVariable("id") Long idServico) {
		return servicoImagensService.encontrarPorServico(idServico);
	}

	  @PostMapping(consumes = "multipart/form-data")
	    public ResponseEntity<ServicoImagem> cadastrar(@RequestParam("file") MultipartFile file, @RequestParam("descricaoImagem") String descricaoImagem, @RequestParam("nomeImagem") String nomeImagem, @RequestParam("cliente_id") Long cliente_id) {
	        ServicoImagem servicoImagem = new ServicoImagem();
	        servicoImagem.setDescricaoImagem(descricaoImagem);
	        servicoImagem.setNomeImagem(nomeImagem);
	        servicoImagem.setCliente_id(cliente_id);
	        
	        servicoImagem = servicoImagensService.cadastrar(servicoImagem, file);
	        
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(servicoImagem.getId())
	                .toUri();
	        
	        return ResponseEntity.created(uri).body(servicoImagem);
	    }
	    
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		servicoImagensService.deletarImagem(id);
		return ResponseEntity.ok().build();
	}

}
