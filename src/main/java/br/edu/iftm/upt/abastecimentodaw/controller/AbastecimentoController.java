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

import br.edu.iftm.upt.abastecimentodaw.modelo.Abastecimento;
import br.edu.iftm.upt.abastecimentodaw.modelo.Usuario;
import br.edu.iftm.upt.abastecimentodaw.modelo.Veiculo;
import br.edu.iftm.upt.abastecimentodaw.repository.Abastecimentos;
import br.edu.iftm.upt.abastecimentodaw.repository.Usuarios;
import br.edu.iftm.upt.abastecimentodaw.repository.Veiculos;
import br.edu.iftm.upt.abastecimentodaw.service.AbastecimentoService;

@Controller
@RequestMapping("/abastecimentos")
public class AbastecimentoController {
	
	private static final Logger logger = LoggerFactory.getLogger(AbastecimentoController.class);
	
	@Autowired
	private Abastecimentos abastecimentos;
	
	@Autowired
	private Veiculos veiculos;

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private AbastecimentoService abastecimentosService;
	
	@GetMapping("/todos")
	public ModelAndView mostrarTodosAbastecimentos() {
		logger.trace("Entrou em mostrarTodosAbastecimentos");
		ModelAndView mv = new ModelAndView("mostrarabastecimentos");
		List<Abastecimento> result = abastecimentos.findAll();
		logger.debug("Abastecimentos encontrados para mostrar {}", result);
		logger.trace("Encaminhando para a view mostrarabastecimentos");
		mv.addObject("abastecimentos", result);
		mv.addObject("titulo", "Todos os abastecimentos");
		return mv;
	}
	
	@PostMapping("/buscar")
	public ModelAndView buscarAbastecimentoPeloId(Long idabastecimento) {
		logger.trace("Entrou em buscarAbastecimentoPeloId");
		logger.debug("idabastecimento recebido para buscar: {}", idabastecimento);
		ModelAndView mv;
		if (idabastecimento != null) {
			Optional<Abastecimento> c = abastecimentos.findById(idabastecimento);
			if (c.isPresent()) {
				logger.debug("abastecimento encontrado: {}", c.get());
				mv = new ModelAndView("mostrarabastecimento");
				logger.trace("Encaminhando para a view mostrarabastecimento");
				mv.addObject("abastecimento", c.get());
			} else {
				logger.debug("Nenhum abastecimento encontrado com esse id");
				mv = new ModelAndView("mostrarmensagem");
				logger.trace("Encaminhando para a view mostrarmensagem");
				mv.addObject("mensagem", "Nenhum abastecimento encontrado com o id " + idabastecimento);
			}
		} else {
			logger.debug("Valor inválido de idabastecimento: null");
			mv = new ModelAndView("mostrarmensagem");
			logger.trace("Encaminhando para a view mostrarmensagem");
			mv.addObject("mensagem", "Use um valor válido de idabastecimento");
		}
		return mv;
	}
	
	
	@PostMapping("/buscarcombustivel")
	public ModelAndView buscarAbastecimentoPeloNome(String tipocombustivel) {
		logger.trace("Entrou em buscarAbastecimentoPeloNome");
		logger.debug("Nome recebido para buscar: {}", tipocombustivel);
		ModelAndView mv;
		if (!tipocombustivel.isBlank()) {
			List<Abastecimento> result = abastecimentos.findByTipocombustivelContainingIgnoreCase(tipocombustivel);
			if (result.size() > 0) {
				logger.debug("abastecimentos encontrados: {}", result);
				mv = new ModelAndView("mostrarabastecimentos");
				logger.trace("Encaminhando para a view mostrarabastecimentos");
				mv.addObject("abastecimentos", result);
				mv.addObject("titulo", "Busca por Tipo de combustivel");
			} else {
				logger.debug("Nenhum abastecimento encontrado com esse nome");
				mv = new ModelAndView("mostrarmensagem");
				logger.trace("Encaminhando para a view mostrarmensagem");
				mv.addObject("mensagem", "Nenhum abastecimento encontrado com o nome " + tipocombustivel);
			}
		} else {
			logger.debug("Valor inválido de nome: blank");
			mv = new ModelAndView("mostrarmensagem");
			logger.trace("Encaminhando para a view mostrarmensagem");
			mv.addObject("mensagem", "Use um valor válido de nome");
		}
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView direcionarParaInsercao(Abastecimento abastecimento) {
		ModelAndView mv = new ModelAndView("novoabastecimento");
		logger.trace("Entrou em direcionarParaInsercao");
		logger.trace("Encaminhando para a view novoabastecimento");
		
		List<Veiculo> todosVeiculos = veiculos.findByStatus(1);
		mv.addObject("veiculos",todosVeiculos);
		
		List<Usuario> todosUsuarios = usuarios.findAll();
		mv.addObject("usuarios", todosUsuarios);
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView inserirNovoAbastecimento(@Valid Abastecimento abastecimento, BindingResult result, RedirectAttributes atributos) {
		logger.trace("Entrou em inserirNovoAbastecimento");
		logger.debug("Abastecimento recebido para inserir: {}", abastecimento);
		if (result.hasErrors()) {
			logger.debug("O abastecimento recebido para inserir não é válido");
			logger.debug("Erros encontrados:");
			for(FieldError erro : result.getFieldErrors()) {
				logger.debug("{}", erro);
			}
			logger.trace("Encaminhando para o método direcionarParaInsercao");
			return direcionarParaInsercao(abastecimento);
		} else {
			abastecimentosService.salvar(abastecimento);
			atributos.addFlashAttribute("mensagem", "Abastecimento inserido com sucesso!");
			logger.trace("Redirecionando para a URL /abastecimentos/novo");
			return new ModelAndView("redirect:/abastecimentos/novo");
		}
	}


	
}
