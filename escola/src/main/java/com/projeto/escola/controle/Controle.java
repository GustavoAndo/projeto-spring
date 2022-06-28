package com.projeto.escola.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controle {
	
	@GetMapping("/login")
	public String verLogin() {
		return "html/geral/login";
	}
	
	@GetMapping("/")
	public String verHome() {
		return "index";
	}
	
}