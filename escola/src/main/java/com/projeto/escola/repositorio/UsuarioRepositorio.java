package com.projeto.escola.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.escola.entidade.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

    @Query("SELECT u FROM Usuario u WHERE u.nome LIKE %?1%"
            + " OR u.id LIKE %?1%"
            + " OR u.nivel_acesso LIKE %?1%"
            + " OR u.username LIKE %?1%")
    public List<Usuario> pesquisa(String palavraChave);

}
