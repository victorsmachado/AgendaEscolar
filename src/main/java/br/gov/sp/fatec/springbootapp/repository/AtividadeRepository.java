package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    public List<Atividade> findByUsuarioNomeContainsIgnoreCase(String usuarioNome);

    public Atividade findByUsuarioNome(String usuarioNome);

}