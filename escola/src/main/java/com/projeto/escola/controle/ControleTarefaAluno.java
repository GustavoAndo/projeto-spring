package com.projeto.escola.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControleTarefaAluno {
	
	@GetMapping("/alunotarefas")
	public String verTarefasAluno() {
		return "html/aluno/alunotarefas";
	}
	
}
