package br.gov.sp.fatec.springbootapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Trabalho;
import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.entity.Atividade;
import br.gov.sp.fatec.springbootapp.entity.Feedback;
import br.gov.sp.fatec.springbootapp.entity.TermosDeUso;
import br.gov.sp.fatec.springbootapp.entity.TermosUsuario;
import br.gov.sp.fatec.springbootapp.entity.TermosFuncional;


import br.gov.sp.fatec.springbootapp.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.repository.TrabalhoRepository;
import br.gov.sp.fatec.springbootapp.repository.AtividadeRepository;
import br.gov.sp.fatec.springbootapp.repository.FeedbackRepository;
import br.gov.sp.fatec.springbootapp.repository.TermosDeUsoRepository;
import br.gov.sp.fatec.springbootapp.repository.TermosUsuarioRepository;
import br.gov.sp.fatec.springbootapp.repository.TermosFuncionalRepository;



@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private AutorizacaoRepository autRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private TrabalhoRepository trabalhoRepo;

    @Autowired
    private AtividadeRepository atividadeRepo;

    @Autowired
    private FeedbackRepository feedbackRepo;

    @Autowired
    private TermosDeUsoRepository termosDeUsoRepo;

    @Autowired
    private TermosUsuarioRepository termosUsuarioRepo;

    @Autowired
    private TermosFuncionalRepository termosFuncionalRepo;


    @Autowired
    private PasswordEncoder passEncoder;

    @Transactional
    public Usuario criarUsuario(String nome, String senha, String autorizacao) {
        Autorizacao aut = autRepo.findByNome(autorizacao);
        if(aut == null) {
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autRepo.save(aut);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(passEncoder.encode(senha));
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        return usuario;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> buscarTodosUsuarios() {
      return usuarioRepo.findAll();
    }

    

    @Override
    public Trabalho buscarTrabalhoPorId(Long id) {
      Optional<Trabalho> trabalhoOp = trabalhoRepo.findById(id);
      if(trabalhoOp.isPresent()) {
        return trabalhoOp.get();
      }
      throw new RegistroNaoEncontradoException("Trabalho não encontrado!");
    }


    
    @Override
    @PreAuthorize("isAuthenticated()")
    public Usuario buscarUsuarioPorNome(String nome) {
      Usuario usuario = usuarioRepo.findByNome(nome);
      if(usuario != null) {
        return usuario;
      }
      throw new RegistroNaoEncontradoException("Usuário não encontrado!");

    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Autorizacao buscarAutorizacaoPorNome(String nome) {
      Autorizacao autorizacao = autRepo.findByNome(nome);
      if(autorizacao != null) {
        return autorizacao;
      }
      throw new RegistroNaoEncontradoException("Autorização não encontrada!");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Usuario usuario = usuarioRepo.findByNome(username);
        if(usuario == null){
          throw new UsernameNotFoundException("Usuario"+ username + "não encontrado!");
        }
      return User.builder().username(username).password(usuario.getSenha())
      .authorities(usuario.getAutorizacoes().stream()
          .map(Autorizacao::getNome).collect(Collectors.toList())
          .toArray(new String[usuario.getAutorizacoes().size()]))
      .build();
    }

    @Override
    public Trabalho criarTrabalho(String titulo, String texto) {
        Trabalho trabalho = new Trabalho();
        trabalho.setTitulo(titulo);
        trabalho.setTexto(texto);
        trabalhoRepo.save(trabalho);
        return trabalho;
    }

    @Override
    public List<Trabalho> buscarTodasTrabalhos() {
      return trabalhoRepo.findAll();
    }

    @Override
	  @PreAuthorize("hasRole('ROLE_ADMIN')")
	  public void excluir(Long idTrabalho) {
		trabalhoRepo.deleteById(idTrabalho);
	}

	  public void excluirAtividade(Long idAtividade) {
		atividadeRepo.deleteById(idAtividade);
	}

    @Override
    public Atividade criarAtividade(String trabalhoId, String usuarioNome, String foto, 
    String nomeFoto, String comentario) {
      Atividade atividade = new Atividade();
      atividade.setTrabalhoId(trabalhoId);
      atividade.setUsuarioNome(usuarioNome);
      atividade.setFoto(foto);
      atividade.setNomeFoto(nomeFoto);
      atividade.setComentario(comentario);
      atividadeRepo.save(atividade);
        return atividade;
    }

    @Override
    public List<Atividade> buscarTodasAtividades() {
      return atividadeRepo.findAll();
    }

  @Override
	public List<Atividade> todos() {
		List<Atividade> retorno = new ArrayList<Atividade>();
		for(Atividade atividade: atividadeRepo.findAll()) {
			retorno.add(atividade);
		}
		return retorno;
	}

  

  @Override
	public List<Atividade> buscar(String nome) {
		return atividadeRepo.findByUsuarioNomeContainsIgnoreCase(nome);
	}

  @Override
	public List<Atividade> buscarById(String id) {
		return atividadeRepo.findByTrabalhoIdContainsIgnoreCase(id);

    
	}

  @Override
    public Atividade buscarAtividadePorId(Long id) {
      Optional<Atividade> atividadeOp = atividadeRepo.findById(id);
      if(atividadeOp.isPresent()) {
        return atividadeOp.get();
      }
      throw new RegistroNaoEncontradoException("Usuário não encontrado!");
    }

  

  public void excluirFeedback(Long idFeedback) {
		feedbackRepo.deleteById(idFeedback);
	}

    @Override
    public Feedback criarFeedback(String trabalhoTexto,  String usuarioNome, 
    String atividadeId, String atividadeImagem, String comentario) {
      Feedback feedback = new Feedback();
      feedback.setTrabalhoTexto(trabalhoTexto);
      feedback.setUsuarioNome(usuarioNome);
      feedback.setAtividadeId(atividadeId);
      feedback.setAtividadeImagem(atividadeImagem);
      feedback.setComentario(comentario);
      feedbackRepo.save(feedback);
        return feedback;
      }

      @Override
	public List<Feedback> buscarFeedback(String nome) {
		return feedbackRepo.findByUsuarioNomeContainsIgnoreCase(nome);
	}

    @Override
    public List<Feedback> buscarTodosFeedbacks() {
      return feedbackRepo.findAll();
    }

  @Override
	public List<Feedback> todosFeedbacks() {
		List<Feedback> retorno = new ArrayList<Feedback>();
		for(Feedback feedback: feedbackRepo.findAll()) {
			retorno.add(feedback);
		}
		return retorno;
	}

  

  

  @Override
  public List<Feedback> buscarFeedbackById(String id){
    return feedbackRepo.findByAtividadeIdContainsIgnoreCase(id);

  }

  @Override
    public List<TermosDeUso> buscarTodosTermos() {
      return termosDeUsoRepo.findAll();
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public Usuario buscarUsuarioPorId(Long id) {
      Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
      if(usuarioOp.isPresent()) {
        return usuarioOp.get();
      }
      throw new RegistroNaoEncontradoException("Usuário não encontrado!");
    }


    public TermosDeUso buscarTermosDeUsoPorVersion(String version) {
      TermosDeUso termosDeUso = termosDeUsoRepo.findByVersion(version);
      if(termosDeUso != null) {
        return termosDeUso;
      }
      throw new RegistroNaoEncontradoException("Termos não encontrado!");

    }

    @Override
    public TermosUsuario criarTermosUsuario(String usuario, String data, String hora, String version,  String empresaDados, String usuarioDados, String empresaParceiraDados) {
      TermosUsuario termosUsuario = new TermosUsuario();
      termosUsuario.setUsuario(usuario);
      termosUsuario.setData(data);
      termosUsuario.setHora(hora);
      termosUsuario.setVersion(version);
      termosUsuario.setEmpresaDados(empresaDados);
      termosUsuario.setUsuarioDados(usuarioDados);
      termosUsuario.setEmpresaParceiraDados(empresaParceiraDados);
      termosUsuarioRepo.save(termosUsuario);
        return termosUsuario;
    }

    @Override
    public TermosFuncional criarTermosFuncional(String usuario, String data, String hora, String version,  String empresaDados, String usuarioDados, String empresaParceiraDados) {
      TermosFuncional termosFuncional = new TermosFuncional();
      termosFuncional.setUsuario(usuario);
      termosFuncional.setData(data);
      termosFuncional.setHora(hora);
      termosFuncional.setVersion(version);
      termosFuncional.setEmpresaDados(empresaDados);
      termosFuncional.setUsuarioDados(usuarioDados);
      termosFuncional.setEmpresaParceiraDados(empresaParceiraDados);
      termosFuncionalRepo.save(termosFuncional);
        return termosFuncional;
    }

    @Override
    public TermosDeUso criarTermosDeUso(String version, String data, String empresaDados, String usuarioDados, String empresaParceiraDados) {
      TermosDeUso termosDeUso = new TermosDeUso();
      termosDeUso.setVersion(version);
      termosDeUso.setData(data);
      termosDeUso.setEmpresaDados(empresaDados);
      termosDeUso.setUsuarioDados(usuarioDados);
      termosDeUso.setEmpresaParceiraDados(empresaParceiraDados);
      termosDeUsoRepo.save(termosDeUso);
        return termosDeUso;
    }

    

    @Override
    public TermosUsuario buscarTermosUsuarioPorUsuario(String usuario) {
      TermosUsuario termosUsuario = termosUsuarioRepo.findByUsuario(usuario);
      if(termosUsuario != null) {
        return termosUsuario;
      }
      throw new RegistroNaoEncontradoException("Termo não encontrado!");

    }

    /*@Override
    public TermosUsuario buscarTermosUsuarioPorUsuarioEVersao(String usuario, String version) {
      TermosUsuario termosUsuario = termosUsuarioRepo.findByUsuarioAndVersion(usuario, version);
      if(termosUsuario != null) {
        return termosUsuario;
      }
      throw new RegistroNaoEncontradoException("Termo não encontrado!");

    }*/


    @Override
    public List<TermosUsuario> buscarTodosTermosUsuario() {
      return termosUsuarioRepo.findAll();
    }

    @Override
	public List<TermosUsuario> buscarTermosUsuario(String usuario, String version) {
		return termosUsuarioRepo.findByUsuarioAndVersion(usuario, version);
	}

    @Override
    public List<TermosFuncional> buscarTodosTermosFuncional() {
      return termosFuncionalRepo.findAll();
    }
    
}