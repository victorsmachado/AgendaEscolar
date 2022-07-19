package br.gov.sp.fatec.springbootapp.service;

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
import br.gov.sp.fatec.springbootapp.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.repository.TrabalhoRepository;
import br.gov.sp.fatec.springbootapp.repository.AtividadeRepository;

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
    private PasswordEncoder passEncoder;

    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public Usuario buscarUsuarioPorId(Long id) {
      Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
      if(usuarioOp.isPresent()) {
        return usuarioOp.get();
      }
      throw new RegistroNaoEncontradoException("Usuário não encontrado!");
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

    @Override
    public Atividade criarAtividade(String trabalhoId, String usuarioNome) {
      Atividade atividade = new Atividade();
      atividade.setTrabalhoId(trabalhoId);
      atividade.setUsuarioNome(usuarioNome);
      atividadeRepo.save(atividade);
        return atividade;
    }

    @Override
    public List<Atividade> buscarTodasAtividades() {
      return atividadeRepo.findAll();
    }
    
}