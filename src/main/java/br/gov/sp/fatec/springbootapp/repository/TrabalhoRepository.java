package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Trabalho;

public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {

    public List<Trabalho> findByTituloContainsIgnoreCase(String titulo);

    public Trabalho findByTitulo(String titulo);





}
