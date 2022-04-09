package com.projeto.teste.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.projeto.teste.entidade.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

}
