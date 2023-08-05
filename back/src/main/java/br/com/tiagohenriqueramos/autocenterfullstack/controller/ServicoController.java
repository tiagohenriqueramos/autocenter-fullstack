package br.com.tiagohenriqueramos.autocenterfullstack.controller;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tiagohenriqueramos.autocenterfullstack.dto.ServicoDTO;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Servico;
import br.com.tiagohenriqueramos.autocenterfullstack.service.ServicoService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/servicos", produces = { "application/json" })
public class ServicoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	ServicoService servicoService;

	@GetMapping
	public ResponseEntity<List<Servico>> listarTodos() {
		List<Servico> lista = servicoService.listarServicos();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Servico> encontrarPorId(@PathVariable Long id) {
		Servico servico = servicoService.buscarPorId(id);
		return ResponseEntity.ok().body(servico);

	}


	@PostMapping
	public ResponseEntity<Servico> cadastrar(@RequestBody Servico servico) {
		servico = servicoService.cadastrarServico(servico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servico.getId())
				.toUri();
		return ResponseEntity.created(uri).body(servico);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		servicoService.deletarServico(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Servico> editar(@PathVariable Long id, @RequestBody Servico servico) {
		servico = servicoService.editarServico(id, servico);
		return ResponseEntity.ok().body(servico);
	}

}
