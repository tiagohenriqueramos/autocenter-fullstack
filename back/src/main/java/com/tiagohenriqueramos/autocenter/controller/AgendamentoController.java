package com.tiagohenriqueramos.autocenter.controller;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.tiagohenriqueramos.autocenter.dto.AgendamentoDTO;
import com.tiagohenriqueramos.autocenter.entities.Agendamento;
import com.tiagohenriqueramos.autocenter.enums.StatusAgendamentos;
import com.tiagohenriqueramos.autocenter.service.AgendamentoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	AgendamentoService agendamentoService;
	
	@GetMapping
	public ResponseEntity<List<Agendamento>> listarTodos() {
		List<Agendamento> lista = agendamentoService.listarAgendamento();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/concluido/{id}")
	public ResponseEntity<Agendamento> concluidoPorId(@PathVariable Long id) {
	    Agendamento agendamento = agendamentoService.buscarPorId(id);

	    if (agendamento == null) {
	        return ResponseEntity.notFound().build();
	    }

	    if (agendamento.getStatus() != StatusAgendamentos.CONCLUIDO) {
	        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
	    }

	    return ResponseEntity.ok(agendamento);
	}

	@GetMapping("/concluidos")
	public ResponseEntity<List<Agendamento>> listarConcluidos() {
		List<Agendamento> lista = agendamentoService.buscarAgendamentosPorStatus(StatusAgendamentos.CONCLUIDO);
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/pendentes")
	public ResponseEntity<List<Agendamento>> listarPendentes() {
		List<Agendamento> lista = agendamentoService.buscarAgendamentosPorStatus(StatusAgendamentos.PENDENTE);
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Agendamento> encontrarPorId(@PathVariable Long id) {
		Agendamento agendamento = agendamentoService.buscarPorId(id);
		return ResponseEntity.ok().body(agendamento);
	}

	@PostMapping
	public ResponseEntity<AgendamentoDTO> cadastrar(@RequestBody AgendamentoDTO agendamento) {
		agendamento = agendamentoService.cadastrarAgendamento(agendamento);
		URI uri = ServletUriComponentsBuilder.fromPath("/{id}").buildAndExpand(agendamento.getId()).toUri();
		return ResponseEntity.created(uri).body(agendamento);
	}

	@PostMapping("/{id}/concluir")
	public ResponseEntity<Agendamento> concluirAgendamento(@PathVariable Long id) {
		Agendamento agendamento = agendamentoService.concluirAgendamento(id);
		if (agendamento != null) {
			return ResponseEntity.ok(agendamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Agendamento> editar(@PathVariable Long id, @RequestBody Agendamento agendamento) {
		agendamento = agendamentoService.editarAgendamento(id, agendamento);
		return ResponseEntity.ok().body(agendamento);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		agendamentoService.deletarAgendamento(id);
		return ResponseEntity.noContent().build();
	}
}
