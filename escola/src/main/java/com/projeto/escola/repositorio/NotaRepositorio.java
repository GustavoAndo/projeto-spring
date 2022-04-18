package com.projeto.escola.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.escola.entidade.Nota;

@Repository
public interface NotaRepositorio extends JpaRepository<Nota, Long>{

}
