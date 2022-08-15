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
@Table(name = "teu_termosusuario")
public class TermosUsuario {


    @JsonView(View.TermosUsuarioCompleto.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teu_id")
    private Long id;
    
    @JsonView({View.TermosUsuarioCompleto.class})
    @Column(name = "teu_usuario")
    private String usuario;

    @JsonView({View.TermosUsuarioCompleto.class})
    @Column(name = "teu_version")
    private String version;

    @JsonView({View.TermosUsuarioCompleto.class})
    @Column(name = "teu_data")
    private String data;

    @JsonView({View.TermosUsuarioCompleto.class})
    @Column(name = "teu_hora")
    private String hora;

    @JsonView({View.TermosUsuarioCompleto.class})
    @Column(name = "teu_empresadados")
    private String empresaDados;

    @JsonView({View.TermosUsuarioCompleto.class})
    @Column(name = "teu_usuariodados")
    private String usuarioDados;

    @JsonView({View.TermosUsuarioCompleto.class})
    @Column(name = "teu_empresaparceiradados")
    private String empresaParceiraDados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEmpresaDados() {
        return empresaDados;
    }

    public void setEmpresaDados(String empresaDados) {
        this.empresaDados = empresaDados;
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
