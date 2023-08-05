package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.ServicoImagem;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.ServicoImagemRepository;

@Service
public class ServicoImagemServiceImpl implements ServicoImagemService {

	@Autowired
	private ServicoImagemRepository servicoImagemRepository;
	

	@Override
	public List<ServicoImagem> listarTodos() {
		return servicoImagemRepository.findAll();
	}
	
	public List<ServicoImagem> buscarPorServico(Long idServico) {
	    Optional<ServicoImagem> optionalImagem = servicoImagemRepository.findById(idServico);

	    List<ServicoImagem> listaImagens = new ArrayList<>();

	    optionalImagem.ifPresent(imagem -> {
	        ServicoImagem servicoImagem = imagem;
	        try (InputStream in = new FileInputStream("C:/imagens/" + servicoImagem.getNomeImagem())) {
	            servicoImagem.setArquivo(IOUtils.toByteArray(in));
	            listaImagens.add(servicoImagem);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    });

	    return listaImagens;
	}

	

	@Override
    public ServicoImagem cadastrarServico(ServicoImagem servicoImagem, MultipartFile file ) {
    	try {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
	            String base64Image = Base64.getEncoder().encodeToString(bytes);

				//servicoImagem.setArquivo(bytes);
                String nomeImagem = String.valueOf("Id da Imagem ID "+servicoImagem.getServicoId())+". "+ file.getOriginalFilename() +" Descrição do Serviço "+ servicoImagem.getNomeImagem() + " "+ file.getOriginalFilename();
				Path caminho = Paths
						.get("c:/imagens/" +nomeImagem );
				Files.write(caminho, bytes);
				servicoImagem.setNomeImagem(nomeImagem);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return servicoImagemRepository.save(servicoImagem);
	}

	@Override
	public void deletarImagem(Long id) {
		ServicoImagem obj = servicoImagemRepository.findById(id).get();
		servicoImagemRepository.delete(obj);
	}


	



	

}
