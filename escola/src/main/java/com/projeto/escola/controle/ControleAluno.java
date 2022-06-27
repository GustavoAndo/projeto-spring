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

import com.projeto.escola.entidade.Aluno;
import com.projeto.escola.entidade.Nota;
import com.projeto.escola.repositorio.AlunoRepositorio;
import com.projeto.escola.repositorio.NotaRepositorio;
import com.projeto.escola.repositorio.UsuarioRepositorio;

@Controller
public class ControleAluno {
	
	@Autowired 
	private AlunoRepositorio alunoRepo;
	
	@Autowired 
	private UsuarioRepositorio usuarioRepo;

	@Autowired
	private NotaRepositorio notaRepo;
	
	@GetMapping("/alunotarefas")
	public String verTarefasAluno() {
		return "html/aluno/alunotarefas";
	}
	
	@GetMapping("/proftarefas")
	public String verTarefasProf() {
		return "html/prof/proftarefas";
	}
	
	@GetMapping("/alunos")
	public String verAlunos(Model modelo, @Param("palavraChave") String palavraChave) {
		if (palavraChave != null) {
			modelo.addAttribute("listaAlunos", alunoRepo.pesquisa(palavraChave));
        } else {
        	modelo.addAttribute("listaAlunos", alunoRepo.findAll());
        }
		modelo.addAttribute("listaUsuarios", usuarioRepo.findAll());
		modelo.addAttribute("palavraChave", palavraChave);
		return "html/read/alunos";
	}
	
	@GetMapping("/cadastrarAlunos")
	public String cadatrarAlunos(@ModelAttribute("aluno") Aluno aluno, Model modelo) {
		aluno.setSerie("");
		modelo.addAttribute("listaUsuarios", usuarioRepo.findAll());
		return "html/create/cadastro-aluno";
	}
	
	@PostMapping("/salvarAlunos")
	public String salvarAlunos(@ModelAttribute("aluno") Aluno aluno, @ModelAttribute("nota") Nota nota, RedirectAttributes redirect) {
		notaRepo.save(nota);
		alunoRepo.save(aluno);
		redirect.addFlashAttribute("sucesso", "Aluno salvo com sucesso!");
		return "redirect:/alunos";
	}
	
	@GetMapping("/editarAlunos/{ra}")
	public String editarAlunos(@PathVariable("ra") Long ra, Model modelo) {
		Optional<Aluno> alunoOpt = alunoRepo.findById(ra);
        if (alunoOpt.isEmpty()) {
        	 return "error/id-invalido";
        }
        modelo.addAttribute("aluno", alunoOpt.get());
        return "html/update/editar-aluno";
	}
	
	@GetMapping("/excluirAlunos/{ra}")
	public String excluirAlunos(@PathVariable("ra") Long ra, Model modelo) {
		Optional<Aluno> alunoOpt = alunoRepo.findById(ra);
        if (alunoOpt.isEmpty()) {
            return "error/id-invalido";
        }
        alunoRepo.deleteById(ra);
		return "redirect:/alunos";
	}

}
