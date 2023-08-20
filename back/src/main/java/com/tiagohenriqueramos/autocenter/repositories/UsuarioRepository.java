package com.tiagohenriqueramos.autocenter.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagohenriqueramos.autocenter.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByNome(String nome);

}
