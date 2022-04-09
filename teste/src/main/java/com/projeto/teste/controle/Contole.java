package com.projeto.teste.controle;

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
import com.projeto.teste.servico.UsuarioServico;

@Controller
public class Contole {
	@Autowired 
	private UsuarioServico usuarioServico;
	
	@GetMapping("/")
	public String verHome() {
		return "index";
	}
	
	@GetMapping("/login")
	public String verLogin() {
		return "login";
	}
	
	@GetMapping("/usuarios")
	public String verUsuarios(Model modelo) {
		modelo.addAttribute("listaUsuarios", usuarioServico.pegarTodosUsuarios());
		return "usuario";
	}
	
	@GetMapping("/cadastrarUsuarios")
	public String cadatrarUsuarios(Model modelo) {
		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);
		usuario.setNivel_acesso("Aluno");
		return "cadastro-usuario";
	}
	
	@PostMapping("/salvarUsuarios")
	public String salvarUsuarios(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return "cadastro-usuario";
		}
		BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder(12);
		String senhaCriptografada = criptografar.encode(usuario.getPassword());
		usuario.setPassword(senhaCriptografada);
		usuarioServico.salvarUsuario(usuario);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/editarUsuarios/{id}")
	public String editarUsuarios(@PathVariable(value = "id") long id, Model modelo) {
		Usuario usuario = usuarioServico.pegarUsuarioPorId(id);
		modelo.addAttribute("usuario", usuario);
		return "editar-usuario";
	}
	
	@GetMapping("/excluirUsuarios/{id}")
	public String excluirUsuarios(@PathVariable(value = "id") long id) {
		usuarioServico.excluirUsuarioPorId(id);
		return "redirect:/usuarios";
	}
}
