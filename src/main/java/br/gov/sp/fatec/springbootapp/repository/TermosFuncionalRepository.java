package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.TermosFuncional;

public interface TermosFuncionalRepository extends JpaRepository<TermosFuncional, Long> {


    
    public TermosFuncional findByUsuario(String usuario);
    public TermosFuncional findByVersion(String version);

    public TermosFuncional findByVersionAndUsuario(String version, String usuario);

  

    

}