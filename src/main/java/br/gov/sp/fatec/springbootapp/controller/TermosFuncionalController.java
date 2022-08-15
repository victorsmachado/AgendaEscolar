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

import br.gov.sp.fatec.springbootapp.repository.TermosFuncionalRepository;
import br.gov.sp.fatec.springbootapp.entity.TermosFuncional;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@RestController
@RequestMapping(value ="/termosfuncional")
@CrossOrigin
public class TermosFuncionalController {
    
 @Autowired
  private SegurancaService segurancaService;

  @Autowired
  private TermosFuncionalRepository termosFuncionalRepository;

  @GetMapping()
  public List<TermosFuncional> buscarTodos() {
    return segurancaService.buscarTodosTermosFuncional();
  }


  @PutMapping("/editar/{usuario}/{version}")
  public ResponseEntity<TermosFuncional> updateTermosFuncional(@PathVariable("version") String version,  @PathVariable("usuario") String usuario, @RequestBody TermosFuncional termosFuncional) {
    TermosFuncional termosFuncionalData = termosFuncionalRepository.findByVersionAndUsuario(version, usuario);
    if (termosFuncionalData != null) {
      TermosFuncional _termosFuncional = termosFuncionalData;

      _termosFuncional.setData(termosFuncional.getData());
      _termosFuncional.setEmpresaDados(termosFuncional.getEmpresaDados());
      _termosFuncional.setUsuarioDados(termosFuncional.getUsuarioDados());
      _termosFuncional.setEmpresaParceiraDados(termosFuncional.getEmpresaParceiraDados());
      return new ResponseEntity<>(termosFuncionalRepository.save(_termosFuncional), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }


  @PostMapping
  public ResponseEntity<TermosFuncional> cadastraNovoTermosFuncional(@RequestBody TermosFuncional termosFuncional,
        UriComponentsBuilder uriComponentsBuilder) {
        termosFuncional = segurancaService.criarTermosFuncional(termosFuncional.getUsuario(), termosFuncional.getData(),
        termosFuncional.getHora(), 
        termosFuncional.getVersion(), termosFuncional.getEmpresaDados(), termosFuncional.getUsuarioDados(), 
        termosFuncional.getEmpresaParceiraDados());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(
        uriComponentsBuilder.path(
            "/termos-usuario/" + termosFuncional.getId()).build().toUri());
    return new ResponseEntity<TermosFuncional>(termosFuncional, responseHeaders, HttpStatus.CREATED);
  }

}
