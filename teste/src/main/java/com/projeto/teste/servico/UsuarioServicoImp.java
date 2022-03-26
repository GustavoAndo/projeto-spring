package com.projeto.teste.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.teste.entidade.Usuario;
import com.projeto.teste.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicoImp implements UsuarioServico {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public List<Usuario> pegarTodosUsuarios(){
		return usuarioRepositorio.findAll();
	}
	
	@Override 
	public void salvarUsuario(Usuario usuario) {
		this.usuarioRepositorio.save(usuario);
	}
	
	@Override
	public Usuario pegarUsuarioPorId(long id) {
		Optional <Usuario> opcional = usuarioRepositorio.findById(id);
		Usuario usuario = null;
		if(opcional.isPresent()) {
			usuario = opcional.get();
		} else {
			throw new RuntimeException("Usuario de id: " + id + " não foi encontrado");
		}
		return usuario;
	}
	
	@Override
	public void excluirUsuarioPorId(long id) {
		this.usuarioRepositorio.deleteById(id);
	}
	
}