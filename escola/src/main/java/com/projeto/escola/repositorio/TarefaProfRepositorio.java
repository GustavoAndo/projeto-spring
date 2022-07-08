package com.projeto.escola.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.escola.entidade.TarefaProf;

@Repository
public interface TarefaProfRepositorio extends JpaRepository<TarefaProf, Long>{
	
	@Query("SELECT t FROM TarefaProf t WHERE t.usuario.username = ?1")
	List<TarefaProf> findAllByThisUser(String username);
	
}