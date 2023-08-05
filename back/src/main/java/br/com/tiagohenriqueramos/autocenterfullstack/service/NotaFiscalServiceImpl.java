package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.NotaFiscal;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.NotaFiscalRepository;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

	private final NotaFiscalRepository notaFiscalRepository;

	@Autowired
	public NotaFiscalServiceImpl(NotaFiscalRepository notaFiscalRepository) {
		this.notaFiscalRepository = notaFiscalRepository;
	}

	@Override
	public NotaFiscal criarNotaFiscal(NotaFiscal notaFiscal) {
		return notaFiscalRepository.save(notaFiscal);
	}

	@Override
	public NotaFiscal buscarNotaFiscalPorId(Long id) {
		return notaFiscalRepository.findById(id).orElse(null);
	}

	@Override
	public List<NotaFiscal> listarTodasNotasFiscais() {
		return notaFiscalRepository.findAll();
	}
}
