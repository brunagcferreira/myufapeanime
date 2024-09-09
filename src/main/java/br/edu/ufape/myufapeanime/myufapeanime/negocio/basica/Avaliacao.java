package br.edu.ufape.myufapeanime.myufapeanime.negocio.basica;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_avaliacoes")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double nota;
    private String comentario;


    @Column(name = "usuario_id")
    private Long usuarioAvaliador;

    @ManyToOne
    private Anime animeAvaliado;

    @Column(name = "anime_id")  //@JoinColumn
    Long animeAvaliadoId;



    public Avaliacao() {}

    public Avaliacao(Double nota, String comentario, Long usuarioAvaliador, Long animeAvaliadoId) {
        this.nota = nota;
        this.comentario = comentario;
        this.usuarioAvaliador = usuarioAvaliador;
        this.animeAvaliadoId = animeAvaliadoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getUsuarioAvaliador() {
        return usuarioAvaliador;
    }

    public void setUsuarioAvaliador(Long usuarioAvaliador) {
        this.usuarioAvaliador = usuarioAvaliador;
    }

    public Long getAnimeAvaliado() {
        return animeAvaliadoId;
    }

    public void setAnimeAvaliado(Long animeAvaliado) {
        this.animeAvaliadoId = animeAvaliado;
    }

}
