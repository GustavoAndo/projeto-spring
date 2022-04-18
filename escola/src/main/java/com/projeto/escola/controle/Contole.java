package com.projeto.escola.controle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.escola.entidade.Aluno;
import com.projeto.escola.entidade.Nota;
import com.projeto.escola.entidade.Usuario;
import com.projeto.escola.repositorio.AlunoRepositorio;
import com.projeto.escola.repositorio.NotaRepositorio;
import com.projeto.escola.repositorio.UsuarioRepositorio;

@Controller
public class Contole {
	
	// REPOSITORY
	
	@Autowired 
	private UsuarioRepositorio usuarioRepo;
	
	@Autowired 
	private AlunoRepositorio alunoRepo;
	
	@Autowired
	private NotaRepositorio notaRepo;
	
	// URL
	
	@GetMapping("/")
	public String verHome() {
		return "index";
	}
	
	@GetMapping("/perfil")
	public String verPerfil() {
		return "html/read/perfil";
	}
	
	@GetMapping("/login")
	public String verLogin() {
		return "html/seguranca/login";
	}
	
	// READ
	
	@GetMapping("/usuarios")
	public String verUsuarios(Model modelo, @Param("palavraChave") String palavraChave) {
		if (palavraChave != null) {
			modelo.addAttribute("listaUsuarios", usuarioRepo.pesquisa(palavraChave));
        } else {
        	modelo.addAttribute("listaUsuarios", usuarioRepo.findAll());
        }
		modelo.addAttribute("palavraChave", palavraChave);
		return "html/read/usuario";
	}
	
	@GetMapping("/alunos")
	public String verAlunos(Model modelo, @Param("palavraChave") String palavraChave) {
		if (palavraChave != null) {
			modelo.addAttribute("listaAlunos", alunoRepo.pesquisa(palavraChave));
        } else {
        	modelo.addAttribute("listaAlunos", alunoRepo.findAll());
        }
		modelo.addAttribute("palavraChave", palavraChave);
		return "html/read/alunos";
	}
	
	// CREATE (PAGES)
	
	@GetMapping("/cadastrarUsuarios")
	public String cadatrarUsuarios(@ModelAttribute("usuario") Usuario usuario) {
		usuario.setNivelAcesso("ROLE_ALUNO");
		return "html/create/cadastro-usuario";
	}
	
	@GetMapping("/cadastrarAlunos")
	public String cadatrarAlunos(@ModelAttribute("aluno") Aluno aluno) {
		aluno.setSerie("");
		return "html/create/cadastro-aluno";
	}
	
	// CREATE (SAVE)
	
	@PostMapping("/salvarUsuarios")
	public String salvarUsuarios(@ModelAttribute("usuario") Usuario usuario) {
	    String encodedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
	    usuario.setPassword(encodedPassword);
	    usuarioRepo.save(usuario);	
		return "redirect:/usuarios";
	}
	
	@PostMapping("/salvarAlunos")
	public String salvarAlunos(@ModelAttribute("aluno") Aluno aluno, @ModelAttribute("nota") Nota nota) {
		nota.setId(aluno.getRa());
		nota.setNome(aluno.getNome());
		notaRepo.save(nota);
		alunoRepo.save(aluno);
		return "redirect:/alunos";
	}
	
	// UPDATE
	
	@GetMapping("/editarUsuarios/{id}")
	public String editarUsuarios(@PathVariable("id") long id, Model modelo) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
        if (usuarioOpt.isEmpty()) {
        	 return "error/id-invalido";
        }
        modelo.addAttribute("usuario", usuarioOpt.get());
        return "html/update/editar-usuario";
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
	
	// DELETE
	
	@GetMapping("/excluirUsuarios/{id}")
	public String excluirUsuarios(@PathVariable("id") long id) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
        if (usuarioOpt.isEmpty()) {
            return "error/id-invalido";
        }
        usuarioRepo.deleteById(id);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/excluirAlunos/{ra}")
	public String excluirAlunos(@PathVariable("ra") Long ra) {
		Optional<Aluno> alunoOpt = alunoRepo.findById(ra);
        if (alunoOpt.isEmpty()) {
            return "error/id-invalido";
        }
        alunoRepo.deleteById(ra);
		return "redirect:/alunos";
	}
}