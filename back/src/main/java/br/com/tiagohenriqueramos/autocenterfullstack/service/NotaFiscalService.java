package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.util.List;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.NotaFiscal;

public interface NotaFiscalService {
    NotaFiscal criarNotaFiscal(NotaFiscal notaFiscal);
    NotaFiscal buscarNotaFiscalPorId(Long id);
    List<NotaFiscal> listarTodasNotasFiscais(); 
}

