package br.edu.iftm.upt.abastecimentodaw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.upt.abastecimentodaw.modelo.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long>{
	List<Usuario> findByNomeContainingIgnoreCase (String nome);
}
