package com.projeto.escola.controle;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.projeto.escola.entidade.TarefaAluno;
import com.projeto.escola.entidade.TarefaProf;
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
	
	@GetMapping("/adicionaTarefa/{id}")
	public String adicionarTarefa(Model modelo, @PathVariable("id") Long id) {
		Optional<TarefaProf> optTarefaProf = repoTarefaProf.findById(id);
		if (optTarefaProf.isEmpty()) {
			return "error/idinvalido";
		}
		modelo.addAttribute("tarefaProf", optTarefaProf.get());
		modelo.addAttribute("tarefaAluno", new TarefaAluno());
		return "html/aluno/adicionartarefa";
	}
	
}
