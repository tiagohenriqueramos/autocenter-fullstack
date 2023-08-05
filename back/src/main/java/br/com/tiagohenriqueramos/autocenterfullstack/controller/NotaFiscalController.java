package br.com.tiagohenriqueramos.autocenterfullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.NotaFiscal;
import br.com.tiagohenriqueramos.autocenterfullstack.service.NotaFiscalService;

@RestController
@RequestMapping(path = "/api/notasfiscais", produces = "application/json")
public class NotaFiscalController {

    private final NotaFiscalService notaFiscalService;

    @Autowired
    public NotaFiscalController(NotaFiscalService notaFiscalService) {
        this.notaFiscalService = notaFiscalService;
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<NotaFiscal> criarNotaFiscal(@RequestBody NotaFiscal notaFiscal) {
    	NotaFiscal novaNotaFiscal = notaFiscalService.criarNotaFiscal(notaFiscal);
        return new ResponseEntity<>(novaNotaFiscal, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<NotaFiscal> buscarNotaFiscalPorId(@PathVariable Long id) {
    	NotaFiscal notaFiscal = notaFiscalService.buscarNotaFiscalPorId(id);
        if (notaFiscal != null) {
            return new ResponseEntity<>(notaFiscal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<NotaFiscal>> listarTodasNotasFiscais() {
        List<NotaFiscal> notasFiscais = notaFiscalService.listarTodasNotasFiscais();
        if (!notasFiscais.isEmpty()) {
            return new ResponseEntity<>(notasFiscais, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


