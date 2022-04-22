package com.projeto.escola.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.escola.entidade.Nota;

@Repository
public interface NotaRepositorio extends JpaRepository<Nota, Long>{

    @Query("SELECT n FROM Nota n WHERE n.nomeAluno LIKE %?1%"
            + " OR n.mediaBio LIKE %?1%"
            + " OR n.mediaMat LIKE %?1%"
            + " OR n.mediaFis LIKE %?1%"
            + " OR n.mediaQui LIKE %?1%"
            + " OR n.mediaGeo LIKE %?1%"
            + " OR n.mediaHis LIKE %?1%"
    		+ " OR n.mediaFil LIKE %?1%"
    		+ " OR n.mediaSoc LIKE %?1%"
    		+ " OR n.mediaPor LIKE %?1%"
    		+ " OR n.mediaEdF LIKE %?1%"
    		+ " OR n.mediaArt LIKE %?1%"
    		+ " OR n.mediaIng LIKE %?1%")
    public List<Nota> pesquisa(String palavraChave);
	
}
