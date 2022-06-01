package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.entity.Anotacao;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value ="/anotacao")
@CrossOrigin
public class AnotacaoController {
    
 @Autowired
  private SegurancaService segurancaService;

  @JsonView(View.AnotacaoCompleto.class)
  @GetMapping()
  public List<Anotacao> buscarTodas() {
    return segurancaService.buscarTodasAnotacoes();
  }

  
  @PostMapping
  public ResponseEntity<Anotacao> cadastraNovoUsuario(@RequestBody Anotacao anotacao,
        UriComponentsBuilder uriComponentsBuilder) {
    anotacao = segurancaService.criarAnotacao(anotacao.getTitulo(), anotacao.getTexto());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(
        uriComponentsBuilder.path(
            "/anotacao/" + anotacao.getId()).build().toUri());
    return new ResponseEntity<Anotacao>(anotacao, responseHeaders, HttpStatus.CREATED);
  }
}
