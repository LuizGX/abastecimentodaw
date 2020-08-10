package br.edu.iftm.upt.abastecimentodaw.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.upt.abastecimentodaw.modelo.Veiculo;
import br.edu.iftm.upt.abastecimentodaw.repository.Veiculos;

@Service
public class VeiculoService {
private static final Logger logger = LoggerFactory.getLogger(AbastecimentoService.class);
	
	@Autowired
	private Veiculos veiculos;
	
	@Transactional
	public void salvar(Veiculo veiculo) {
		logger.trace("Entrou em salvar");
		logger.debug("Salvando o abastecimento {}", veiculo);
		veiculo.setStatus(1);
		veiculos.save(veiculo);
		logger.debug("Abastecimento salvo com sucesso {}", veiculo);
	}
	
	@Transactional
	public void alterar(Veiculo veiculo) {
		logger.trace("Entrou em alterar");
		logger.debug("Alterando o veiculo {}", veiculo);
		veiculos.save(veiculo);
		logger.debug("Veiculo alterado com sucesso {}", veiculo);
	}
	
	@Transactional
	public void remover(Veiculo veiculo) {
		logger.trace("Entrou em remover");
		logger.debug("Removendo o veiculo {}", veiculo);
		veiculo.setStatus(0);
		veiculos.save(veiculo);
		logger.debug("Veiculo removido com sucesso {}", veiculo);
	}


}
