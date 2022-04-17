package com.projeto.escola.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.escola.entidade.Aluno;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Long>{

    @Query("SELECT a FROM Aluno a WHERE a.nome LIKE %?1%"
            + " OR a.ra LIKE %?1%"
            + " OR a.serie LIKE %?1%"
            + " OR a.dataNascimento LIKE %?1%")
    public List<Aluno> pesquisa(String palavraChave);
    
}