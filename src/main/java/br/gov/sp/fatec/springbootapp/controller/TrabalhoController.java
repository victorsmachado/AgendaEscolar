package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.entity.Trabalho;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;
import br.gov.sp.fatec.springbootapp.repository.TrabalhoRepository;

@RestController
@RequestMapping(value ="/trabalho")
@CrossOrigin
public class TrabalhoController {

  @Autowired
  private TrabalhoRepository trabalhoRepo;
    
 @Autowired
  private SegurancaService segurancaService;

  @JsonView(View.TrabalhoCompleto.class)
  @GetMapping()
  public List<Trabalho> buscarTodas() {
    return segurancaService.buscarTodasTrabalhos();
  }

  @JsonView(View.TrabalhoCompleto.class)
  @GetMapping(value = "/{id}")
  public Trabalho buscarPorId(@PathVariable("id") Long id) {
    return segurancaService.buscarTrabalhoPorId(id);
  }

  
  @PostMapping
  public ResponseEntity<Trabalho> cadastraNovoTrabalho(@RequestBody Trabalho trabalho,
        UriComponentsBuilder uriComponentsBuilder) {
    trabalho = segurancaService.criarTrabalho(trabalho.getTitulo(), trabalho.getTexto());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(
        uriComponentsBuilder.path(
            "/trabalho/" + trabalho.getId()).build().toUri());
    return new ResponseEntity<Trabalho>(trabalho, responseHeaders, HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<HttpStatus> deleteTrabalho(@PathVariable("id") long id) {
    try {
      trabalhoRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

  
    
	

