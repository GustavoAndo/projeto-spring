package com.projeto.escola.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aluno {
	@Id
	long RA;
	
	String nome;
	
	@Column(name = "data_nascimento")
	Date dataNascimento;
	
	String serie;
	
	@Column(name = "id_nota")
	long idNota;
	
	@Column(name = "id_presenca")
	long idPresenca;
	
	public long getRA() {
		return RA;
	}
	public void setRA(long rA) {
		RA = rA;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public long getIdNota() {
		return idNota;
	}
	public void setIdNota(long idNota) {
		this.idNota = idNota;
	}
	public long getIdPresenca() {
		return idPresenca;
	}
	public void setIdPresenca(long idPresenca) {
		this.idPresenca = idPresenca;
	}
}
