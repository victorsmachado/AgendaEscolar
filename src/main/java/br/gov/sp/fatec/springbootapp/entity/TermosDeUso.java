package br.gov.sp.fatec.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controller.View;

@Entity
@Table(name = "tdu_termosdeuso")
public class TermosDeUso {


    @JsonView(View.TermosDeUsoCompleto.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tdu_id")
    private Long id;
    
    @JsonView({View.TermosDeUsoCompleto.class})
    @Column(name = "tdu_version")
    private String version;

    @JsonView({View.TermosDeUsoCompleto.class})
    @Column(name = "tdu_data")
    private String data;

    @JsonView({View.TermosDeUsoCompleto.class})
    @Column(name = "tdu_empresadados")
    private String empresaDados;

    @JsonView({View.TermosDeUsoCompleto.class})
    @Column(name = "tdu_usuariodados")
    private String usuarioDados;

    @JsonView({View.TermosDeUsoCompleto.class})
    @Column(name = "tdu_empresaparceiradados")
    private String empresaParceiraDados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpresaDados() {
        return empresaDados;
    }

    public void setEmpresaDados(String empresaDados) {
        this.empresaDados = empresaDados;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsuarioDados() {
        return usuarioDados;
    }

    public void setUsuarioDados(String usuarioDados) {
        this.usuarioDados = usuarioDados;
    }

    public String getEmpresaParceiraDados() {
        return empresaParceiraDados;
    }

    public void setEmpresaParceiraDados(String empresaParceiraDados) {
        this.empresaParceiraDados = empresaParceiraDados;
    }


}