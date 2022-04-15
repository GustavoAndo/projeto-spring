package com.projeto.escola.controle;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.escola.entidade.Usuario;
import com.projeto.escola.repositorio.UsuarioRepositorio;

@Controller
public class Contole {
	
	// Repositorios
	
	@Autowired 
	private UsuarioRepositorio usuarioRepo;
	
	// Páginas
	
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
	
	@GetMapping("/usuarios")
	public String verUsuarios(Model modelo) {
		modelo.addAttribute("listaUsuarios", usuarioRepo.findAll());
		return "html/read/usuario";
	}
	
	@GetMapping("/cadastrarUsuarios")
	public String cadatrarUsuarios(@ModelAttribute("usuario") Usuario usuario) {
		usuario.setNivel_acesso("ROLE_ALUNO");
		return "html/create/cadastro-usuario";
	}
	
	@PostMapping("/salvarUsuarios")
	public String salvarUsuarios(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return "html/create/cadastro-usuario";
		}
	    String encodedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
	    usuario.setPassword(encodedPassword);
	    usuarioRepo.save(usuario);	
		return "redirect:/usuarios";
	}
	
	@GetMapping("/editarUsuarios/{id}")
	public String editarUsuarios(@PathVariable("id") long id, Model modelo) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
        if (usuarioOpt.isEmpty()) {
        	 return "html/seguranca/id-invalido";
        }
        modelo.addAttribute("usuario", usuarioOpt.get());
        return "html/update/editar-usuario";
	}
	
	@GetMapping("/excluirUsuarios/{id}")
	public String excluirUsuarios(@PathVariable("id") long id) {
		Optional<Usuario> concessionariaOpt = usuarioRepo.findById(id);
        if (concessionariaOpt.isEmpty()) {
            return "html/seguranca/id-invalido";
        }
        usuarioRepo.deleteById(id);
		return "redirect:/usuarios";
	}
}
