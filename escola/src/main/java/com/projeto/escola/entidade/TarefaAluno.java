package com.projeto.escola.entidade;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TarefaAluno {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "data_entregue")
	private LocalDate dataEntregue;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_arquivo", referencedColumnName = "id")
	private Arquivo arquivo;
	
	@ManyToOne
	@JoinColumn(name = "id_tarefa_prof")
	private TarefaProf tarefaProf;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDataEntregue() {
		return dataEntregue;
	}
	public void setDataEntregue(LocalDate dataEntregue) {
		this.dataEntregue = dataEntregue;
	}
	public Arquivo getArquivo() {
		return arquivo;
	}
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
	public TarefaProf getTarefaProf() {
		return tarefaProf;
	}
	public void setTarefaProf(TarefaProf tarefaProf) {
		this.tarefaProf = tarefaProf;
	}
}
