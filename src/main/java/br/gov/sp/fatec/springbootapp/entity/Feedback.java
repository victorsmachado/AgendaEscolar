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
@Table(name = "fed_feedback")
public class Feedback {


    @JsonView(View.FeedbackCompleto.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fed_id")
    private Long id;
    
    @JsonView({View.FeedbackCompleto.class})
    @Column(name = "ant_texto")
    private String trabalhoTexto;

    @JsonView({View.FeedbackCompleto.class})
    @Column(name = "usr_nome")
    private String usuarioNome;

    @JsonView({View.FeedbackCompleto.class})
    @Column(name = "atv_id")
    private String atividadeId;

    @JsonView({View.FeedbackCompleto.class})
    @Column(name = "atv_img")
    private String atividadeImagem;

    @JsonView({View.FeedbackCompleto.class})
    @Column(name = "fed_comentario")
    private String comentario;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrabalhoTexto() {
        return trabalhoTexto;
    }

    public void setTrabalhoTexto(String trabalhoTexto) {
        this.trabalhoTexto = trabalhoTexto;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(String atividadeId) {
        this.atividadeId = atividadeId;
    }

    public String getAtividadeImagem() {
        return atividadeImagem;
    }

    public void setAtividadeImagem(String atividadeImagem) {
        this.atividadeImagem = atividadeImagem;
    }


    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}