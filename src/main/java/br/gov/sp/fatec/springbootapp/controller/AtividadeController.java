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

import br.gov.sp.fatec.springbootapp.entity.Atividade;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value ="/atividade")
@CrossOrigin
public class AtividadeController {
    
 @Autowired
  private SegurancaService segurancaService;

  @JsonView(View.AtividadeCompleto.class)
  @GetMapping()
  public List<Atividade> buscarTodas() {
    return segurancaService.buscarTodasAtividades();
  }

  

  
  @PostMapping
  public ResponseEntity<Atividade> cadastraNovaAtividade(@RequestBody Atividade atividade,
        UriComponentsBuilder uriComponentsBuilder) {
    atividade = segurancaService.criarAtividade(atividade.getTrabalhoId(), atividade.getUsuarioNome());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(
        uriComponentsBuilder.path(
            "/atividade/" + atividade.getId()).build().toUri());
    return new ResponseEntity<Atividade>(atividade, responseHeaders, HttpStatus.CREATED);
  }
}
