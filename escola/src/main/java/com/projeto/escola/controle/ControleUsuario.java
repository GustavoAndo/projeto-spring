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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.escola.entidade.Usuario;
import com.projeto.escola.repositorio.UsuarioRepositorio;

@Controller
public class ControleUsuario {
	
	@Autowired 
	private UsuarioRepositorio usuarioRepo;
	
	@GetMapping("/perfil")
	public String verPerfil(Model modelo) {
		modelo.addAttribute("listaUsuarios", usuarioRepo.findAll());
		return "html/read/perfil";
	}
	
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
	
	@GetMapping("/cadastrarUsuarios")
	public String cadatrarUsuarios(@ModelAttribute("usuario") Usuario usuario) {
		usuario.setNivelAcesso("ROLE_ALUNO");
		return "html/create/cadastro-usuario";
	}
	
	@PostMapping("/salvarUsuarios")
	public String salvarUsuarios(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirect) {
	    String encodedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
	    usuario.setPassword(encodedPassword);
	    usuarioRepo.save(usuario);	
	    redirect.addFlashAttribute("sucesso", "Usuário salvo com sucesso!");
		return "redirect:/usuarios";
	}
	
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
	
	@GetMapping("/excluirUsuarios/{id}")
	public String excluirUsuarios(@PathVariable("id") long id) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
        if (usuarioOpt.isEmpty()) {
            return "error/id-invalido";
        }
        usuarioRepo.deleteById(id);
		return "redirect:/usuarios";
	}
}
