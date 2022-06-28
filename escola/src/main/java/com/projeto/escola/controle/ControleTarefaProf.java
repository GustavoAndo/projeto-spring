package com.projeto.escola.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControleTarefaProf {
	
	@GetMapping("/proftarefas")
	public String verTarefasProf() {
		return "html/prof/proftarefas";
	}
	
	@GetMapping("/novaTarefa")
	public String novaTarefa() {
		return "html/prof/novatarefa";
	}
	
}
