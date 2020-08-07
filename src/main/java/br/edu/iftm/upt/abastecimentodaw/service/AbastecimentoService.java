package br.edu.iftm.upt.abastecimentodaw.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.upt.abastecimentodaw.modelo.Abastecimento;
import br.edu.iftm.upt.abastecimentodaw.repository.Abastecimentos;

@Service
public class AbastecimentoService {
	private static final Logger logger = LoggerFactory.getLogger(AbastecimentoService.class);
	
	@Autowired
	private Abastecimentos abastecimentos;
	
	@Transactional
	public void salvar(Abastecimento abastecimento) {
		logger.trace("Entrou em salvar");
		logger.debug("Salvando o abastecimento {}", abastecimento);
		abastecimentos.save(abastecimento);
		logger.debug("Abastecimento salvo com sucesso {}", abastecimento);
	}
}
