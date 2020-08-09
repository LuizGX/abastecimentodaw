package br.edu.iftm.upt.abastecimentodaw.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.upt.abastecimentodaw.modelo.Veiculo;
import br.edu.iftm.upt.abastecimentodaw.repository.Veiculos;
import br.edu.iftm.upt.abastecimentodaw.service.VeiculoService;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {
	
	private static final Logger logger = LoggerFactory.getLogger(AbastecimentoController.class);
	
	@Autowired
	private Veiculos veiculos;
	
	@Autowired
	VeiculoService veiculoService;
	
	@GetMapping("/todos")
	public ModelAndView mostrarTodosVeiculos() {
		logger.trace("Entrou em mostrarTodosVeiculos");
		ModelAndView mv = new ModelAndView("veiculo/mostrarveiculos");
		List<Veiculo> result = veiculos.findAll();
		logger.debug("Veiculos encontrados para mostrar {}", result);
		logger.trace("Encaminhando para a view mostrarveiculos");
		mv.addObject("veiculos", result);
		mv.addObject("titulo", "Todos os veiculos");
		return mv;
	}
	
	@PostMapping("/buscar")
	public ModelAndView buscarVeiculoPeloId(Long idveiculo) {
		logger.trace("Entrou em buscarVeiculoPeloId");
		logger.debug("idveiculo recebido para buscar: {}", idveiculo);
		ModelAndView mv;
		if (idveiculo != null) {
			Optional<Veiculo> c = veiculos.findById(idveiculo);
			if (c.isPresent()) {
				logger.debug("veiculo encontrado: {}", c.get());
				mv = new ModelAndView("veiculo/mostrarveiculo");
				logger.trace("Encaminhando para a view mostrarveiculo");
				mv.addObject("veiculo", c.get());
			} else {
				logger.debug("Nenhum veiculo encontrado com esse id");
				mv = new ModelAndView("mostrarmensagem");
				logger.trace("Encaminhando para a view mostrarmensagem");
				mv.addObject("mensagem", "Nenhum abastecimento encontrado com o id " + idveiculo);
			}
		} else {
			logger.debug("Valor inválido de idabastecimento: null");
			mv = new ModelAndView("mostrarmensagem");
			logger.trace("Encaminhando para a view mostrarmensagem");
			mv.addObject("mensagem", "Use um valor válido de idveiculo");
		}
		return mv;
	}
	
	@GetMapping("/novoVeiculo")
	public ModelAndView direcionarParaInsercaoVeiculo(Veiculo veiculo) {
		logger.trace("Entrou em direcionarParaInsercao");
		logger.trace("Encaminhando para a view novoveiculo");
		return new ModelAndView("veiculo/novoveiculo");
	}
	
	@PostMapping("novoVeiculo")
	public ModelAndView inserirNovoVeiculo(@Valid Veiculo veiculo, BindingResult result, RedirectAttributes atributos) {
		logger.trace("Entrou em inserirNovoVeiculo");
		logger.debug("Veiculo recebido para inserir: {}", veiculo);
		if (result.hasErrors()) {
			logger.debug("O veiculo recebido para inserir não é válido");
			logger.debug("Erros encontrados:");
			for(FieldError erro : result.getFieldErrors()) {
				logger.debug("{}", erro);
			}
			logger.trace("Encaminhando para o método direcionarParaInsercao");
			return direcionarParaInsercaoVeiculo(veiculo);
		} else {
			veiculoService.salvar(veiculo);
			atributos.addFlashAttribute("mensagem", "Veiculo inserido com sucesso!");
			logger.trace("Redirecionando para a URL /veiculos/novo");
			return new ModelAndView("redirect:/veiculos/novoVeiculo");
		}
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterarVeiculo(Veiculo veiculo) {
		logger.trace("Entrou em alterarVeiculo");
		logger.debug("Veiculo recebido para alterar: {}", veiculo);		
		veiculoService.alterar(veiculo);
		ModelAndView mv = new ModelAndView("mostrarmensagem");
		mv.addObject("mensagem", "Veiculo alterado com sucesso!");
		logger.trace("Encaminhando para a view mostrarmensagem");
		return mv;
	}

	@PostMapping("/confirmarremocao")
	public String confirmarRemocao(Veiculo veiculo) {
		logger.trace("Entrou em confirmarRemocao");
		logger.debug("Veiculo recebido para confirmar a remocao: {}", veiculo);		
		logger.trace("Encaminhando para a view confirmarremocao");		
		return "veiculo/confirmacaoremocao";
	}

	@PostMapping("/remover")
	public ModelAndView removerVeiculo(Long idveiculo) {
		logger.trace("Entrou em removerVeiculo");
		logger.debug("idContato recebido para remover: {}", idveiculo);
		ModelAndView mv;
		logger.debug("Buscando o contato para remover");
		Optional<Veiculo> veiculo = veiculos.findById(idveiculo);
		if (veiculo.isPresent()) {
			logger.debug("Removendo o contato {}", veiculo.get());
			veiculoService.remover(veiculo.get());
			mv = new ModelAndView("mostrarmensagem");
			mv.addObject("mensagem", "Veiculo removido com sucesso!");
			logger.trace("Encaminhando para a view mostrarmensagem");
		} else {
			logger.debug("Nenhum veiculo encontrado para remover com esse id");
			mv = new ModelAndView("mostrarmensagem");
			logger.trace("Encaminhando para a view mostrarmensagem");
			mv.addObject("mensagem", "Nenhum veiculo encontrado para remover com o id " + idveiculo);
		}
		return mv;
	}

	
	
	

}
