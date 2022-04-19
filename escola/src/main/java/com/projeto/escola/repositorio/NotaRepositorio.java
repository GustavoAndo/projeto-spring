package com.projeto.escola.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.escola.entidade.Nota;

@Repository
public interface NotaRepositorio extends JpaRepository<Nota, Long>{

    @Query("SELECT n FROM Nota n WHERE n.nome LIKE %?1%"
            + " OR n.id LIKE %?1%"
            + " OR n.atividade1 LIKE %?1%"
            + " OR n.atividade2 LIKE %?1%"
            + " OR n.atividade3 LIKE %?1%"
            + " OR n.atividade4 LIKE %?1%"
            + " OR n.mediaAtividades LIKE %?1%"
            + " OR n.prova1 LIKE %?1%"
            + " OR n.prova2 LIKE %?1%"
    		+ " OR n.media LIKE %?1%")
    public List<Nota> pesquisa(String palavraChave);
	
}
