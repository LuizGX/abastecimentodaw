package br.edu.iftm.upt.abastecimentodaw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.upt.abastecimentodaw.modelo.Veiculo;

public interface Veiculos extends JpaRepository<Veiculo, Long>{
	List<Veiculo> findByModeloContainingIgnoreCase (String modelo);
}
