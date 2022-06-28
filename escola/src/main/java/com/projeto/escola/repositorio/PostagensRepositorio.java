package com.projeto.escola.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.escola.entidade.Postagens;

@Repository
public interface PostagensRepositorio extends JpaRepository<Postagens, Long>{
}
