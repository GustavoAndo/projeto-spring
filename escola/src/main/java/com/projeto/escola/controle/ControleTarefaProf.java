package com.projeto.escola.controle;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.escola.entidade.TarefaProf;
import com.projeto.escola.entidade.Usuario;
import com.projeto.escola.repositorio.TarefaProfRepositorio;
import com.projeto.escola.repositorio.UsuarioRepositorio;

@Controller
public class ControleTarefaProf {
	
	@Autowired
	private TarefaProfRepositorio repoTarefaProf;
	@Autowired
	private UsuarioRepositorio repoUsuario;
	
	@GetMapping("/profTodasTarefas")
	public String verTarefasProf(Model modelo, @AuthenticationPrincipal User user) {
		modelo.addAttribute("todasTarefas", repoTarefaProf.findAllByThisUser(user.getUsername()));
		modelo.addAttribute("dataAtual", LocalDate.now());
		return "html/prof/proftarefas";
	}
	
	@GetMapping("/novaTarefa")
	public String novaTarefa(Model modelo) {
		modelo.addAttribute("tarefaProf", new TarefaProf());
		modelo.addAttribute("titulo", "Nova Tarefa");
		modelo.addAttribute("botao", "Adicionar");
		return "html/prof/novatarefa";
	}
	
	@PostMapping("/salvarNovaTarefa")
	public String salvarNovaTarefa(@ModelAttribute("tarefaProf") TarefaProf tarefaProf, @AuthenticationPrincipal User user) {
		Usuario usuario = repoUsuario.findByEmail(user.getUsername());
		tarefaProf.setUsuario(usuario);
		repoTarefaProf.save(tarefaProf);
		return "redirect:/profTodasTarefas";
	}
	
	@GetMapping("/profEditarTarefa/{id}")
	public String profEditarTarefa(Model modelo, @PathVariable("id") Long id) {
		Optional<TarefaProf> optTarefaProf = repoTarefaProf.findById(id);
		if (optTarefaProf.isEmpty()) {
			return "error/idinvalido";
		}
		modelo.addAttribute("tarefaProf", optTarefaProf.get());
		modelo.addAttribute("titulo", "Editar Tarefa");
		modelo.addAttribute("botao", "Editar");
		return "html/prof/novatarefa";
	}
	
	@GetMapping("/profExcluirTarefa/{id}")
	public String profExcluirTarefa(@PathVariable("id") Long id) {
		Optional<TarefaProf> optTarefaProf = repoTarefaProf.findById(id);
		if (optTarefaProf.isEmpty()) {
			return "error/idinvalido";
		}
		repoTarefaProf.deleteById(id);
		return "redirect:/profTodasTarefas";
	}
}
