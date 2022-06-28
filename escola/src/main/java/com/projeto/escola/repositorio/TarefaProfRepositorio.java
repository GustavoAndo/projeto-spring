package com.projeto.escola.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.escola.entidade.TarefaProf;

@Repository
public interface TarefaProfRepositorio extends JpaRepository<TarefaProf, Long>{
}