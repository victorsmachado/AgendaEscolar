package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.repository.TermosUsuarioRepository;
import br.gov.sp.fatec.springbootapp.entity.TermosUsuario;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value ="/termosusuario")
@CrossOrigin
public class TermosUsuarioController {
    
 @Autowired
  private SegurancaService segurancaService;

  @Autowired
  private TermosUsuarioRepository termosUsuarioRepository;

  
  @GetMapping()
  public List<TermosUsuario> buscarTodos() {
    return segurancaService.buscarTodosTermosUsuario();
  }


  @GetMapping(value = "/{usuario}")
  public TermosUsuario buscarPorUsuario(@PathVariable("usuario") String usuario) {
    return segurancaService.buscarTermosUsuarioPorUsuario(usuario);
  }

  /*@GetMapping(value = "/buscar/{usuario}/{version}")
  public TermosUsuario buscarPorUsuarioEVersao(@PathVariable("usuario") String usuario, @PathVariable("version") String version) {
    return segurancaService.buscarTermosUsuarioPorUsuarioEVersao(usuario, version);
  }*/

  @GetMapping(value = "/lista/{usuario}/{version}")
  public List<TermosUsuario> listarPorUsuarioVersao(@PathVariable("usuario") String usuario, @PathVariable("version") String version) {
    return segurancaService.buscarTermosUsuario(usuario, version);
  }

  


  @PostMapping
  public ResponseEntity<TermosUsuario> cadastraNovoTermosUsuario(@RequestBody TermosUsuario termosUsuario,
        UriComponentsBuilder uriComponentsBuilder) {
        termosUsuario = segurancaService.criarTermosUsuario(termosUsuario.getUsuario(), termosUsuario.getData(),
        termosUsuario.getHora(), 
        termosUsuario.getVersion(), termosUsuario.getEmpresaDados(), termosUsuario.getUsuarioDados(), 
        termosUsuario.getEmpresaParceiraDados());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(
        uriComponentsBuilder.path(
            "/termos-usuario/" + termosUsuario.getId()).build().toUri());
    return new ResponseEntity<TermosUsuario>(termosUsuario, responseHeaders, HttpStatus.CREATED);
  }

}
