
package br.com.tiagohenriqueramos.autocenterfullstack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Usuario;

	public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	
	boolean existsByEmail(String email);
	
	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByNome(String nome);

	

}
