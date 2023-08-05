package br.com.tiagohenriqueramos.autocenterfullstack.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.ServicoImagem;
import br.com.tiagohenriqueramos.autocenterfullstack.service.ServicoImagemService;

@RestController
@RequestMapping("/servicos/imagem")
@CrossOrigin(origins = "*")
@MultipartConfig
public class ServicoImagemController {

	@Autowired
	private ServicoImagemService servicoImagensService;

	@GetMapping
	public List<ServicoImagem> buscarTodos() {
		return servicoImagensService.listarTodos();
	}

	@GetMapping("/{id}")
	public List<ServicoImagem> buscarPorProduto(@PathVariable("id") Long idServico) {
		return servicoImagensService.buscarPorServico(idServico);
	}

	  @PostMapping(consumes = "multipart/form-data")
	    public ResponseEntity<ServicoImagem> cadastrar(@RequestParam("file") MultipartFile file, @RequestParam("descricaoImagem") String descricaoImagem, @RequestParam("nomeImagem") String nomeImagem, @RequestParam("servicoId") Long servicoId) {
	        ServicoImagem servicoImagem = new ServicoImagem();
	        servicoImagem.setDescricaoImagem(descricaoImagem);
	        servicoImagem.setNomeImagem(nomeImagem);
	        servicoImagem.setServicoId(servicoId);
	        
	        servicoImagem = servicoImagensService.cadastrarServico(servicoImagem, file);
	        
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(servicoImagem.getId())
	                .toUri();
	        
	        return ResponseEntity.created(uri).body(servicoImagem);
	    }
	    
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		servicoImagensService.deletarImagem(id);
		return ResponseEntity.ok().build();
	}

}
