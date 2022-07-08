package com.projeto.escola;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.escola.entidade.Usuario;
import com.projeto.escola.repositorio.UsuarioRepositorio;

@Component
@Transactional
public class UsuarioInicial implements CommandLineRunner {

	@Autowired
	private UsuarioRepositorio usuarioRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
	    String encodedPassword = new BCryptPasswordEncoder().encode("123");
		
		Usuario usuario = new Usuario();
		usuario.setUsername("gustavo@gmail.com");
		usuario.setPassword(encodedPassword);
		usuario.setNome("Gustavo Ando");
		usuario.setNivelAcesso("ROLE_ADMINISTRADOR");
		usuario.setAtivo(1);
		usuarioRepo.save(usuario);
		
	}

}