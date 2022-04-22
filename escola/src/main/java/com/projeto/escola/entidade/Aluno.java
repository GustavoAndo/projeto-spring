package com.projeto.escola.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Aluno {
	@Id
	private Long ra;
	
	private String cpf;

	private String nome;
	
	private String serie;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	private Long idNota;
	
	public Long getRa() {
		return ra;
	}
	public void setRa(Long ra) {
		this.ra = ra;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	public Long getIdNota() {
		return idNota;
	}
	public void setIdNota(Long idNota) {
		this.idNota = idNota;
	}
}
