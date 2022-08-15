package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.repository.TermosDeUsoRepository;
import br.gov.sp.fatec.springbootapp.entity.TermosDeUso;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value ="/termos")
@CrossOrigin
public class TermosDeUsoController {
    
 @Autowired
  private SegurancaService segurancaService;

  @Autowired
  private TermosDeUsoRepository termosDeUsoRepo;

  @JsonView(View.TermosDeUsoCompleto.class)
  @GetMapping()
  public List<TermosDeUso> buscarTodos() {
    return segurancaService.buscarTodosTermos();
  }

}
