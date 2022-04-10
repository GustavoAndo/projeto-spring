package com.projeto.teste.controle;

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

import com.projeto.teste.entidade.Usuario;
import com.projeto.teste.repositorio.UsuarioRepositorio;

@Controller
public class Contole {
	@Autowired 
	private UsuarioRepositorio usuarioRepo;
	
	@GetMapping("/")
	public String verHome() {
		return "index";
	}
	
	@GetMapping("/perfil")
	public String verPerfil() {
		return "perfil";
	}
	
	@GetMapping("/login")
	public String verLogin() {
		return "login";
	}
	
	@GetMapping("/usuarios")
	public String verUsuarios(Model modelo) {
		modelo.addAttribute("listaUsuarios", usuarioRepo.findAll());
		return "usuario";
	}
	
	@GetMapping("/cadastrarUsuarios")
	public String cadatrarUsuarios(@ModelAttribute("usuario") Usuario usuario) {
		usuario.setNivel_acesso("ROLE_ALUNO");
		return "cadastro-usuario";
	}
	
	@PostMapping("/salvarUsuarios")
	public String salvarUsuarios(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
		usuarioRepo.save(usuario);	
		if(result.hasErrors()) {
			return "cadastro-usuario";
		}
	    String encodedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
	    usuario.setPassword(encodedPassword);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/editarUsuarios/{id}")
	public String editarUsuarios(@PathVariable("id") long id, Model modelo) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
        if (usuarioOpt.isEmpty()) {
        	return "id-invalido";
        }
        modelo.addAttribute("usuario", usuarioOpt.get());
        return "editar-usuario";
	}
	
	@GetMapping("/excluirUsuarios/{id}")
	public String excluirUsuarios(@PathVariable("id") long id) {
		Optional<Usuario> concessionariaOpt = usuarioRepo.findById(id);
        if (concessionariaOpt.isEmpty()) {
            return "id-invalido";
        }
        usuarioRepo.deleteById(id);
		return "redirect:/usuarios";
	}
	
}
