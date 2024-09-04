package br.edu.ufape.myufapeanime.myufapeanime.negocio.basica;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double nota;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioAvaliador;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;

    public Avaliacao() {}

    public Avaliacao(Double nota, String comentario, Usuario usuarioAvaliador) {
        this.nota = nota;
        this.comentario = comentario;
        this.usuarioAvaliador = usuarioAvaliador;
    }

    //contrutor para testes
    public Avaliacao(Double nota, String comentario) {
        this.nota = nota;
        this.comentario = comentario;
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

    public Usuario getUsuarioAvaliador() {
        return usuarioAvaliador;
    }

    public void setUsuarioAvaliador(Usuario usuarioAvaliador) {
        this.usuarioAvaliador = usuarioAvaliador;
    }

}
