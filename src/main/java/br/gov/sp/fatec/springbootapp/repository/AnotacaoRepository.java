package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Anotacao;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

    public List<Anotacao> findByTituloContainsIgnoreCase(String titulo);

    public Anotacao findByTitulo(String titulo);





}
