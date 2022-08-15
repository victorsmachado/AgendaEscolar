package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.TermosUsuario;

public interface TermosUsuarioRepository extends JpaRepository<TermosUsuario, Long> {


    
    public TermosUsuario findByUsuario(String usuario);
    public TermosUsuario findByVersion(String version);

    public List<TermosUsuario> findByUsuarioAndVersion(String version, String usuario);


  

    

}