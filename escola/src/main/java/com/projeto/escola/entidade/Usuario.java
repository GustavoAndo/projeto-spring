package com.projeto.escola.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String nome;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String serie;
	
	@Column
	private String disciplina;
	
	@Column(name = "nivel_acesso")
	private String nivelAcesso;
	
	@Column(columnDefinition = "tinyint", length = 1)
	private int ativo = 1;
	
	@Lob
	@Column(name = "imagem_perfil")
	private byte[] imagemPerfil;

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
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getNivelAcesso() {
		return nivelAcesso;
	}
	public void setNivelAcesso(String nivelAcesso) {
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
}
