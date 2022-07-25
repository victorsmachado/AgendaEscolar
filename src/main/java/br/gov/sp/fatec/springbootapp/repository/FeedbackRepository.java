package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    public List<Feedback> findByUsuarioNomeContainsIgnoreCase(String usuarioNome);

    public List<Feedback> findByAtividadeIdContainsIgnoreCase(String atividadeId);

    public Feedback findByUsuarioNome(String usuarioNome);

    public Feedback findByAtividadeId(String atividadeId);


  

    

}