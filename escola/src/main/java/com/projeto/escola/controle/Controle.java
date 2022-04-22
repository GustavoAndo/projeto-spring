package com.projeto.escola.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class Controle {
	
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
	public String verPerfil(Model modelo) {
		modelo.addAttribute("listaUsuarios", usuarioRepo.findAll());
		return "html/read/perfil";
	}
	
	@GetMapping("/login")
	public String verLogin() {
		return "html/seguranca/login";
	}
	
	// READ
	
	@GetMapping("/usuarios")
	public String verUsuarios(Model modelo, @Param("palavraChave") String palavraChave) {
		return paginaUsuario(modelo, 1, "id", "asc", null);
	}
	
	@GetMapping("/pagina/{numeroPagina}")
	public String paginaUsuario(Model modelo, @PathVariable("numeroPagina") int paginaAtual,
											@Param("sortField") String sortField,
											@Param("sortDir") String sortDir,
											@Param("palavraChave") String palavraChave) {
		Sort ordenar = Sort.by(sortField);
    	ordenar = sortDir.equals("asc") ? ordenar.ascending() : ordenar.descending();
    	Pageable paginas = PageRequest.of(paginaAtual - 1, 7, ordenar);
    	if (palavraChave != null) {
			Page<Usuario> pagina = usuarioRepo.pesquisa(palavraChave, paginas);
			long totalItens = pagina.getTotalElements();
	    	int totalPaginas = pagina.getTotalPages();
	    	List<Usuario> listaUsuarios = pagina.getContent();
	    	modelo.addAttribute("totalItens", totalItens);
	    	modelo.addAttribute("totalPaginas", totalPaginas);
	    	modelo.addAttribute("listaUsuarios", listaUsuarios);
		} else {
			Page<Usuario> pagina = usuarioRepo.findAll(paginas);
			long totalItens = pagina.getTotalElements();
	    	int totalPaginas = pagina.getTotalPages();
	    	List<Usuario> listaUsuarios = pagina.getContent();
	    	modelo.addAttribute("totalItens", totalItens);
	    	modelo.addAttribute("totalPaginas", totalPaginas);
	    	modelo.addAttribute("listaUsuarios", listaUsuarios);
		}
    	modelo.addAttribute("paginaAtual", paginaAtual);
    	modelo.addAttribute("sortField", sortField);
    	modelo.addAttribute("sortDir", sortDir);
    	String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
    	modelo.addAttribute("reverseSortDir", reverseSortDir);
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
	
	@GetMapping("/notas")
	public String verNotas(Model modelo, @Param("palavraChave") String palavraChave) {
		if (palavraChave != null) {
			modelo.addAttribute("listaNotas", notaRepo.pesquisa(palavraChave));
        } else {
        	modelo.addAttribute("listaNotas", notaRepo.findAll());
        }
		modelo.addAttribute("palavraChave", palavraChave);
		return "html/read/notas";
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
		nota.setRaAluno(aluno.getRa());
		nota.setNomeAluno(aluno.getNome());
		notaRepo.save(nota);
		alunoRepo.save(aluno);
		return "redirect:/alunos";
	}
	
	@PostMapping("/salvarNotas")
	public String salvarNotas(@ModelAttribute("nota") Nota nota) {
		notaRepo.save(nota);
		return "redirect:/notas";
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
	
	@GetMapping("/senhaUsuarios/{id}")
	public String redfinirSenha(@PathVariable("id") long id, Model modelo) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
        if (usuarioOpt.isEmpty()) {
        	 return "error/id-invalido";
        }
        modelo.addAttribute("usuario", usuarioOpt.get());
        return "html/update/redefinir-senha";
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
	
	@GetMapping("/editarNotas/{ra}")
	public String editarNotas(@PathVariable("ra") Long ra, Model modelo) {
		Optional<Nota> notaOpt = notaRepo.findById(ra);
        if (notaOpt.isEmpty()) {
        	 return "error/id-invalido";
        }
        modelo.addAttribute("nota", notaOpt.get());
        return "html/update/editar-nota";
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
		Optional<Nota> notaOpt = notaRepo.findById(ra);
        if (alunoOpt.isEmpty() || notaOpt.isEmpty()) {
            return "error/id-invalido";
        }
        alunoRepo.deleteById(ra);
        notaRepo.deleteById(ra);
		return "redirect:/alunos";
	}
}