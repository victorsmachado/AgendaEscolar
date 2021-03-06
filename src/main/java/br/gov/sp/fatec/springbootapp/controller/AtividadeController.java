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

import br.gov.sp.fatec.springbootapp.repository.AtividadeRepository;
import br.gov.sp.fatec.springbootapp.entity.Atividade;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value ="/atividade")
@CrossOrigin
public class AtividadeController {
    
 @Autowired
  private SegurancaService segurancaService;

  @Autowired
  private AtividadeRepository atividadeRepo;

  @JsonView(View.AtividadeCompleto.class)
  @GetMapping()
  public List<Atividade> buscarTodas() {
    return segurancaService.buscarTodasAtividades();
  }

  

  
  @PostMapping
  public ResponseEntity<Atividade> cadastraNovaAtividade(@RequestBody Atividade atividade,
        UriComponentsBuilder uriComponentsBuilder) {
    atividade = segurancaService.criarAtividade(atividade.getTrabalhoId(), atividade.getUsuarioNome(), atividade.getFoto(), atividade.getNomeFoto(), atividade.getComentario());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(
        uriComponentsBuilder.path(
            "/atividade/" + atividade.getId()).build().toUri());
    return new ResponseEntity<Atividade>(atividade, responseHeaders, HttpStatus.CREATED);
  }

  @GetMapping(value = "/{nome}")
	@JsonView(View.AtividadeCompleto.class)
	public ResponseEntity<Collection<Atividade>> pesquisar(@PathVariable("nome") String nome) {
		return new ResponseEntity<Collection<Atividade>>(segurancaService.buscar(nome), HttpStatus.OK);
	}

  @GetMapping(value = "/id/{id}")
	@JsonView(View.AtividadeCompleto.class)
	public ResponseEntity<Collection<Atividade>> pesquisarPorId(@PathVariable("id") String id) {
		return new ResponseEntity<Collection<Atividade>>(segurancaService.buscarById(id), HttpStatus.OK);
	}

  @JsonView(View.AtividadeCompleto.class)
  @GetMapping(value = "/atvid/{id}")
  public Atividade buscarPorId(@PathVariable("id") Long id) {
    return segurancaService.buscarAtividadePorId(id);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<HttpStatus> deleteAtividade(@PathVariable("id") long id) {
    try {
      atividadeRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
