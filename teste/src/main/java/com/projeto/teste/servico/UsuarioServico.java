package com.projeto.teste.servico;

import java.util.List;

import com.projeto.teste.entidade.Usuario;

public interface UsuarioServico {
	List <Usuario> pegarTodosUsuarios();
	void salvarUsuario(Usuario usuario);
	Usuario pegarUsuarioPorId(long id);
	void excluirUsuarioPorId (long id);
}