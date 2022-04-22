package com.projeto.escola.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.escola.entidade.Contato;

public interface ContatoRepositorio extends JpaRepository<Contato, Long> {

}
