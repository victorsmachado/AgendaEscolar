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
@Table(name = "atv_atividade")
public class Atividade {


    @JsonView(View.AtividadeCompleto.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atv_id")
    private Long id;
    
    @JsonView({View.AtividadeCompleto.class})
    @Column(name = "ant_id")
    private String trabalhoId;

    @JsonView({View.AtividadeCompleto.class})
    @Column(name = "usr_nome")
    private String usuarioNome;

    @JsonView({View.AtividadeCompleto.class})
    @Column(name = "atv_img")
    private String foto;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrabalhoId() {
        return trabalhoId;
    }

    public void setTrabalhoId(String trabalhoId) {
        this.trabalhoId = trabalhoId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
