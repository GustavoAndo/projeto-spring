package com.projeto.escola.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.projeto.escola.entidade.Usuario;

@Repository
public interface UsuarioRepositorio extends PagingAndSortingRepository<Usuario, Long>{

    @Query("SELECT u FROM Usuario u WHERE u.nome LIKE %?1%"
            + " OR u.id LIKE %?1%"
            + " OR u.nivelAcesso LIKE %?1%"
            + " OR u.username LIKE %?1%")
    public Page<Usuario> pesquisa(String palavraChave, Pageable pagina);

}
