package com.projeto.escola.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.escola.entidade.TarefaAluno;

@Repository
public interface TarefaAlunoRepositorio extends JpaRepository<TarefaAluno, Long>{
}
