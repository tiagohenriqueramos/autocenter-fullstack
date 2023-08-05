//cada metodo é uma rota e cada rota tem seu metodos rest
package br.com.tiagohenriqueramos.autocenterfullstack.controller;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.tiagohenriqueramos.autocenterfullstack.dto.AgendamentoDTO;
import br.com.tiagohenriqueramos.autocenterfullstack.dto.ClienteDTO;
import br.com.tiagohenriqueramos.autocenterfullstack.dto.ProdutoDTO;
import br.com.tiagohenriqueramos.autocenterfullstack.dto.ServicoDTO;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Agendamento;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Cliente;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Produto;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Servico;
import br.com.tiagohenriqueramos.autocenterfullstack.enums.StatusAgendamentos;
import br.com.tiagohenriqueramos.autocenterfullstack.service.AgendamentoService;
import br.com.tiagohenriqueramos.autocenterfullstack.service.ClienteService;
import br.com.tiagohenriqueramos.autocenterfullstack.service.ProdutoService;
import br.com.tiagohenriqueramos.autocenterfullstack.service.ServicoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	AgendamentoService agendamentoService;

	@Autowired
	ClienteService clienteService;

	@Autowired
	ServicoService servicoService;

	@Autowired
	ProdutoService produtoService;


	@GetMapping
	public ResponseEntity<List<Agendamento>> listarTodos() {
		List<Agendamento> lista = agendamentoService.listarAgendamentos();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping("/listar/concluido")
	public ResponseEntity<List<AgendamentoDTO>> listarAgendamentosConcluidos() {
        List<Agendamento> agendamentosConcluidos = agendamentoService.buscarAgendamentosPorStatus(StatusAgendamentos.CONCLUIDO);
		List<AgendamentoDTO> agendamentoDTOs = new ArrayList<>();
		
		for (Agendamento agendamento : agendamentosConcluidos) {
			Cliente cliente = clienteService.buscarPorId(agendamento.getCliente_Id());
			Servico servico = servicoService.buscarPorId(agendamento.getServicoId());
			Produto produto = produtoService.buscarPorId(agendamento.getServicoId());

			ClienteDTO clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(),
					cliente.getEmail(), cliente.getTelefone());

			ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricaoProduto(),
					produto.getPreco());

			ServicoDTO servicoDTO = new ServicoDTO(servico.getId(), servico.getDescricaoServico(),
					servico.getMaoDeObra(), produtoDTO);

			AgendamentoDTO agendamentoDTO = new AgendamentoDTO(agendamento.getId(), agendamento.getData(),
					agendamento.getHorario(), clienteDTO, servicoDTO, agendamento.getStatus());

			agendamentoDTOs.add(agendamentoDTO);
		}

		return ResponseEntity.ok(agendamentoDTOs);
	}

	@GetMapping(value = "/listar")
	public ResponseEntity<List<AgendamentoDTO>> listarTodosObj() {
		List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
		List<AgendamentoDTO> agendamentoDTOs = new ArrayList<>();

		for (Agendamento agendamento : agendamentos) {
			Cliente cliente = clienteService.buscarPorId(agendamento.getCliente_Id());
			Servico servico = servicoService.buscarPorId(agendamento.getServicoId());
			Produto produto = produtoService.buscarPorId(agendamento.getServicoId());

			ClienteDTO clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(),
					cliente.getEmail(), cliente.getTelefone());

			ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricaoProduto(),
					produto.getPreco());

			ServicoDTO servicoDTO = new ServicoDTO(servico.getId(), servico.getDescricaoServico(),
					servico.getMaoDeObra(), produtoDTO);

			AgendamentoDTO agendamentoDTO = new AgendamentoDTO(agendamento.getId(), agendamento.getData(),
					agendamento.getHorario(), clienteDTO, servicoDTO, agendamento.getStatus());

			agendamentoDTOs.add(agendamentoDTO);
		}

		return ResponseEntity.ok(agendamentoDTOs);
	}

	@GetMapping(value = "/listar/{id}")
	public ResponseEntity<AgendamentoDTO> listarPorId(@PathVariable Long id) {
		Agendamento agendamento = agendamentoService.buscarPorId(id);

		if (agendamento == null) {
			return ResponseEntity.notFound().build();
		}

		Cliente cliente = clienteService.buscarPorId(agendamento.getCliente_Id());
		Servico servico = servicoService.buscarPorId(agendamento.getServicoId());
		Produto produto = produtoService.buscarPorId(agendamento.getServicoId());

		ClienteDTO clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(),
				cliente.getTelefone());

		ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricaoProduto(),
				produto.getPreco());

		ServicoDTO servicoDTO = new ServicoDTO(servico.getId(), servico.getDescricaoServico(), servico.getMaoDeObra(),
				produtoDTO);

		AgendamentoDTO agendamentoDTO = new AgendamentoDTO(agendamento.getId(), agendamento.getData(),
				agendamento.getHorario(), clienteDTO, servicoDTO, agendamento.getStatus());

		return ResponseEntity.ok(agendamentoDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Agendamento> encontrarPorId(@PathVariable Long id) {
		Agendamento agendamento = agendamentoService.buscarPorId(id);
		return ResponseEntity.ok().body(agendamento);

	}
	@GetMapping(value = "/{id}/concluido")
	public ResponseEntity<AgendamentoDTO> buscarConcluidoPorId(@PathVariable Long id) {
	    Agendamento agendamento = agendamentoService.buscarPorId(id);

	    if (agendamento == null || agendamento.getStatus() != StatusAgendamentos.CONCLUIDO) {
	        return ResponseEntity.notFound().build();
	    }

	    Cliente cliente = clienteService.buscarPorId(agendamento.getCliente_Id());
	    Servico servico = servicoService.buscarPorId(agendamento.getServicoId());
	    Produto produto = produtoService.buscarPorId(agendamento.getServicoId());

	    ClienteDTO clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(),
	            cliente.getTelefone());

	    ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricaoProduto(),
	            produto.getPreco());

	    ServicoDTO servicoDTO = new ServicoDTO(servico.getId(), servico.getDescricaoServico(), servico.getMaoDeObra(),
	            produtoDTO);

	    AgendamentoDTO agendamentoDTO = new AgendamentoDTO(agendamento.getId(), agendamento.getData(),
	            agendamento.getHorario(), clienteDTO, servicoDTO, agendamento.getStatus());

	    return ResponseEntity.ok(agendamentoDTO);
	}

	@PostMapping
	public ResponseEntity<Agendamento> cadastrar(@RequestBody Agendamento agendamento) {
		agendamento = agendamentoService.cadastrarAgendamento(agendamento);
		URI uri = ServletUriComponentsBuilder.fromPath("/{id}").buildAndExpand(agendamento.getId()).toUri();
		System.out.println(agendamento);
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
