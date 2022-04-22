package com.projeto.escola.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Disciplina {
	@Id
	private Long id;
	
	@Column(name="nome_aluno")
	private String nomeAluno;
	
	private String disciplina;
	
	private float atividade1;
	private float atividade2;
	private float atividade3;
	private float atividade4;
	
	@Column(name="media_atividades")
	private float mediaAtividades;
	
	private float prova1;
	private float prova2;
	private float media;
	
	@Column(name="id_nota")
	private long idNota;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public float getAtividade1() {
		return atividade1;
	}
	public void setAtividade1(float atividade1) {
		this.atividade1 = atividade1;
	}
	public float getAtividade2() {
		return atividade2;
	}
	public void setAtividade2(float atividade2) {
		this.atividade2 = atividade2;
	}
	public float getAtividade3() {
		return atividade3;
	}
	public void setAtividade3(float atividade3) {
		this.atividade3 = atividade3;
	}
	public float getAtividade4() {
		return atividade4;
	}
	public void setAtividade4(float atividade4) {
		this.atividade4 = atividade4;
	}
	public float getMediaAtividades() {
		return mediaAtividades;
	}
	public void setMediaAtividades(float mediaAtividades) {
		this.mediaAtividades = mediaAtividades;
	}
	public float getProva1() {
		return prova1;
	}
	public void setProva1(float prova1) {
		this.prova1 = prova1;
	}
	public float getProva2() {
		return prova2;
	}
	public void setProva2(float prova2) {
		this.prova2 = prova2;
	}
	public float getMedia() {
		return media;
	}
	public void setMedia(float media) {
		this.media = media;
	}
	
}