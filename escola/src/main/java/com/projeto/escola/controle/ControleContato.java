package com.projeto.escola.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.escola.entidade.Contato;
import com.projeto.escola.repositorio.ContatoRepositorio;

@Controller
public class ControleContato {
	
	@Autowired
	private ContatoRepositorio contatoRepo;
	
	@GetMapping("/contato")
	public String contatar(@ModelAttribute("contato") Contato contato) {
		return "html/create/contato";
	}
	
	@PostMapping("/salvarMensagem")
	public String salvarNotas(@ModelAttribute("contato") Contato contato, RedirectAttributes redirect) {
		contatoRepo.save(contato);
		redirect.addFlashAttribute("sucesso", "Mensagem enviada com sucesso!");
		return "redirect:/contato";
	}
	
}
