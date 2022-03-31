package com.projeto.teste.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="O nome deve obrigatoriamente ser preenchido")
	private String nome;
	
	@NotBlank(message="O e-mail deve obrigatoriamente ser preenchido")
	@Email(message="Endereço de e-mail inválido")
	private String login;
	
	@Size(min=8, max=16, message="A senha deve estar entre 8 a 16 digitos")
	private String senha;

	private String nivel_acesso;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNivel_acesso() {
		return nivel_acesso;
	}

	public void setNivel_acesso(String nivel_acesso) {
		this.nivel_acesso = nivel_acesso;
	}
}
