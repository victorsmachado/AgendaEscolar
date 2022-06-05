package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.springbootapp.entity.Trabalho;
import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

public interface SegurancaService extends UserDetailsService{

    public Usuario criarUsuario(String nome, String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();

    public Usuario buscarUsuarioPorId(Long id);

    public Usuario buscarUsuarioPorNome(String nome);

    public Autorizacao buscarAutorizacaoPorNome(String nome);

    public Trabalho criarTrabalho(String titulo, String texto);

    public List<Trabalho> buscarTodasTrabalhos();
    
}