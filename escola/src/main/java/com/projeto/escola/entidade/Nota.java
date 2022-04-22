package com.projeto.escola.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome_aluno")
	private String nomeAluno;
	
	@Column(name="media_bio")
	private float mediaBio;
	
	@Column(name="media_mat")
	private float mediaMat;
	
	@Column(name="media_fis")
	private float mediaFis;
	
	@Column(name="media_qui")
	private float mediaQui;
	
	@Column(name="media_geo")
	private float mediaGeo;
	
	@Column(name="media_his")
	private float mediaHis;
	
	@Column(name="media_fil")
	private float mediaFil;
	
	@Column(name="media_soc")
	private float mediaSoc;
	
	@Column(name="media_por")
	private float mediaPor;
	
	@Column(name="media_edf")
	private float mediaEdF;
	
	@Column(name="media_art")
	private float mediaArt;
	
	@Column(name="media_ing")
	private float mediaIng;

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

	public float getMediaBio() {
		return mediaBio;
	}

	public void setMediaBio(float mediaBio) {
		this.mediaBio = mediaBio;
	}

	public float getMediaMat() {
		return mediaMat;
	}

	public void setMediaMat(float mediaMat) {
		this.mediaMat = mediaMat;
	}

	public float getMediaFis() {
		return mediaFis;
	}

	public void setMediaFis(float mediaFis) {
		this.mediaFis = mediaFis;
	}

	public float getMediaQui() {
		return mediaQui;
	}

	public void setMediaQui(float mediaQui) {
		this.mediaQui = mediaQui;
	}

	public float getMediaGeo() {
		return mediaGeo;
	}

	public void setMediaGeo(float mediaGeo) {
		this.mediaGeo = mediaGeo;
	}

	public float getMediaHis() {
		return mediaHis;
	}

	public void setMediaHis(float mediaHis) {
		this.mediaHis = mediaHis;
	}

	public float getMediaFil() {
		return mediaFil;
	}

	public void setMediaFil(float mediaFil) {
		this.mediaFil = mediaFil;
	}

	public float getMediaSoc() {
		return mediaSoc;
	}

	public void setMediaSoc(float mediaSoc) {
		this.mediaSoc = mediaSoc;
	}

	public float getMediaPor() {
		return mediaPor;
	}

	public void setMediaPor(float mediaPor) {
		this.mediaPor = mediaPor;
	}

	public float getMediaEdF() {
		return mediaEdF;
	}

	public void setMediaEdF(float mediaEdF) {
		this.mediaEdF = mediaEdF;
	}

	public float getMediaArt() {
		return mediaArt;
	}

	public void setMediaArt(float mediaArt) {
		this.mediaArt = mediaArt;
	}

	public float getMediaIng() {
		return mediaIng;
	}

	public void setMediaIng(float mediaIng) {
		this.mediaIng = mediaIng;
	}
}