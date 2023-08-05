package br.com.tiagohenriqueramos.autocenterfullstack.service;

import java.util.List;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	void validarNome(String nome);


	List<Usuario> listarUsuarios();
	
	Usuario encontrarPorId(Long id);
	
	void deletarUsuarioPorId(Long id);

}
