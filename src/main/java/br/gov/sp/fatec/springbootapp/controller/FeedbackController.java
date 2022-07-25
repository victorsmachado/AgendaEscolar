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

import br.gov.sp.fatec.springbootapp.repository.FeedbackRepository;
import br.gov.sp.fatec.springbootapp.entity.Feedback;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value ="/feedback")
@CrossOrigin
public class FeedbackController {
    
 @Autowired
  private SegurancaService segurancaService;

  @Autowired
  private FeedbackRepository feedbackRepo;

  @JsonView(View.FeedbackCompleto.class)
  @GetMapping()
  public List<Feedback> buscarTodos() {
    return segurancaService.buscarTodosFeedbacks();
  }

  

  
  @PostMapping
  public ResponseEntity<Feedback> cadastraNovoFeedback(@RequestBody Feedback feedback,
        UriComponentsBuilder uriComponentsBuilder) {
        feedback = segurancaService.criarFeedback(feedback.getTrabalhoTexto(), feedback.getUsuarioNome(), feedback.getAtividadeId(), feedback.getAtividadeImagem(), feedback.getComentario());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(
        uriComponentsBuilder.path(
            "/feedback/" + feedback.getId()).build().toUri());
    return new ResponseEntity<Feedback>(feedback, responseHeaders, HttpStatus.CREATED);
  }

  @GetMapping(value = "/{nome}")
  @JsonView(View.FeedbackCompleto.class)
	public ResponseEntity<Collection<Feedback>> pesquisar(@PathVariable("nome") String nome) {
		return new ResponseEntity<Collection<Feedback>>(segurancaService.buscarFeedback(nome), HttpStatus.OK);
	}

  @GetMapping(value = "/id/{id}")
  @JsonView(View.FeedbackCompleto.class)
	public ResponseEntity<Collection<Feedback>> pesquisarId(@PathVariable("id") String id) {
		return new ResponseEntity<Collection<Feedback>>(segurancaService.buscarFeedbackById(id), HttpStatus.OK);
	}

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<HttpStatus> deleteFeedback(@PathVariable("id") long id) {
    try {
      feedbackRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
