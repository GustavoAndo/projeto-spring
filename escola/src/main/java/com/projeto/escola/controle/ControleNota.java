package com.projeto.escola.controle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.escola.entidade.Nota;
import com.projeto.escola.repositorio.AlunoRepositorio;
import com.projeto.escola.repositorio.NotaRepositorio;

@Controller
public class ControleNota {
	
	@Autowired
	private NotaRepositorio notaRepo;
	
	@Autowired
	private AlunoRepositorio alunoRepo;
	
	@GetMapping("/notas")
	public String verNotas(Model modelo, @Param("palavraChave") String palavraChave) {
		if (palavraChave != null) {
			// modelo.addAttribute("listaNotas", notaRepo.pesquisa(palavraChave));
        } else {
        	modelo.addAttribute("listaNotas", notaRepo.findAll());
        }
		modelo.addAttribute("listaAlunos", alunoRepo.findAll());
		modelo.addAttribute("palavraChave", palavraChave);
		return "html/read/notas";
	}

	@GetMapping("/cadastrarNota")
	public String cadastrarNota(@ModelAttribute("nota") Nota nota, Model modelo) {
		modelo.addAttribute("listaAlunos", alunoRepo.findAll());
		return "html/create/cadastro-nota";
	}
	
	@PostMapping("/salvarNotas")
	public String salvarNotas(@ModelAttribute("nota") Nota nota, RedirectAttributes redirect) {
		notaRepo.save(nota);
		redirect.addFlashAttribute("sucesso" , "Nota salva com sucesso!");
		return "redirect:/notas";
	}
	
	@GetMapping("/editarNotas/{id}")
	public String editarNotas(@PathVariable("id") Long id, Model modelo) {
		Optional<Nota> notaOpt = notaRepo.findById(id);
        if (notaOpt.isEmpty()) {
        	 return "error/id-invalido";
        }
        modelo.addAttribute("nota", notaOpt.get());
        return "html/update/editar-nota";
	}
	
}
