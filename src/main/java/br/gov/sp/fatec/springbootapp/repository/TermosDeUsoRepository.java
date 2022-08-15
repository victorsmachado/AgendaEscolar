package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.TermosDeUso;

public interface TermosDeUsoRepository extends JpaRepository<TermosDeUso, Long> {

    public TermosDeUso findByVersion(String version);

  

    

}