package br.edu.iftm.upt.abastecimentodaw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.upt.abastecimentodaw.modelo.Abastecimento;

public interface Abastecimentos extends JpaRepository<Abastecimento, Long>{
	List<Abastecimento> findByTipocombustivelContainingIgnoreCase (String tipocombustivel);
}
 