package com.projeto.escola.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="O nome deve obrigatoriamente ser preenchido")
	private String nome;
	
	@NotBlank(message="O e-mail deve obrigatoriamente ser preenchido")
	@Email(message="Endereço de e-mail inválido")
	private String username;
	
	@Size(min=8, message="A senha deve possuir mais de 8 digitos")
	private String password;
	
	@Column(name = "nivel_acesso")
	private String nivelAcesso;
	
	@Column(columnDefinition = "tinyint", length = 1)
	private int ativo = 1;
	
	@Lob
	@Column(name = "imagem_perfil")
	private byte[] imagemPerfil;
	
	@NotBlank(message="O CPF deve obrigatoriamente ser preenchido")
	@CPF
	private String cpf;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivel_acesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public byte[] getImagemPerfil() {
		return imagemPerfil;
	}

	public void setImagemPerfil(byte[] imagemPerfil) {
		this.imagemPerfil = imagemPerfil;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
