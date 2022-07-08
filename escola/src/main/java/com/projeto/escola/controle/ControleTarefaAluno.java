package com.projeto.escola.controle;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.projeto.escola.repositorio.TarefaProfRepositorio;

@Controller
public class ControleTarefaAluno {
	
	@Autowired
	TarefaProfRepositorio repoTarefaProf;
	
	@GetMapping("/alunoTodasTarefas")
	public String verTarefasAluno(Model modelo) {
		modelo.addAttribute("todasTarefas", repoTarefaProf.findAll());
		modelo.addAttribute("dataAtual", LocalDate.now());
		return "html/aluno/alunotarefas";
	}
	
}
