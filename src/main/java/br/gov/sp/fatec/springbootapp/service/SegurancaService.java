package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.springbootapp.entity.Trabalho;
import br.gov.sp.fatec.springbootapp.entity.Atividade;
import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.entity.Feedback;
import br.gov.sp.fatec.springbootapp.entity.TermosDeUso;
import br.gov.sp.fatec.springbootapp.entity.TermosUsuario;
import br.gov.sp.fatec.springbootapp.entity.TermosFuncional;

public interface SegurancaService extends UserDetailsService{

    public Usuario criarUsuario(String nome, String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();

    public Usuario buscarUsuarioPorId(Long id);

    public Trabalho buscarTrabalhoPorId(Long id);

    public Usuario buscarUsuarioPorNome(String nome);

    public Autorizacao buscarAutorizacaoPorNome(String nome);

    public Trabalho criarTrabalho(String titulo, String texto);

    public List<Trabalho> buscarTodasTrabalhos();

    public void excluir(Long idTrabalho);

    public void excluirAtividade(Long idAtividade);

    public Atividade criarAtividade(String trabalhoId, String usuarioNome, String foto, String nomeFoto, String comentario);

    public List<Atividade> buscarTodasAtividades();

    public List<Atividade> todos();

    public List<Atividade> buscar(String nome);

    public List<Atividade> buscarById(String id);

    public Atividade buscarAtividadePorId(Long id);

    public Feedback criarFeedback(String trabalhoTexto, String usuarioNome, String atividadeId, String atividadeImagem, String comentario);

    public List<Feedback> buscarTodosFeedbacks();

    public List<Feedback> todosFeedbacks();

    public List<Feedback> buscarFeedback(String nome);

    public List<Feedback> buscarFeedbackById(String id);

    public void excluirFeedback(Long idFeedback);

    public List<TermosDeUso> buscarTodosTermos();

    public TermosDeUso buscarTermosDeUsoPorVersion(String version);

    public TermosDeUso criarTermosDeUso(String version, String data, String empresaDados, String usuarioDados, String empresaParceiraDados);

    public TermosUsuario criarTermosUsuario(String usuario, String version, String data, String hora, String empresaDados, String usuarioDados, String empresaParceiraDados);

    public TermosUsuario buscarTermosUsuarioPorUsuario(String usuario);

    //public TermosUsuario buscarTermosUsuarioPorUsuarioEVersao(String usuario, String version);

    public List<TermosUsuario> buscarTodosTermosUsuario();

    public List<TermosUsuario> buscarTermosUsuario(String usuario, String version);

    public List<TermosFuncional> buscarTodosTermosFuncional();

    public TermosFuncional criarTermosFuncional(String usuario, String version, String data, String hora, String empresaDados, String usuarioDados, String empresaParceiraDados);
    
    
}