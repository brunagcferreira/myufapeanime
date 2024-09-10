package br.edu.ufape.myufapeanime.myufapeanime.dto;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;

public class AvaliacaoComIdDTO {


    private Long id;
    private Double nota;
    private String comentario;
    private Long usuarioAvaliador;
    private Long AnimeAvaliado;


    public AvaliacaoComIdDTO () {}

    public AvaliacaoComIdDTO(Double nota, String comentario, Long usuarioAvaliador, Long AnimeAvaliado) {
        this.nota = nota;
        this.comentario = comentario;
        this.usuarioAvaliador = usuarioAvaliador;
        this.AnimeAvaliado = AnimeAvaliado;
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
        return AnimeAvaliado;
    }

    public void setAnimeAvaliado(Long AnimeAvaliado) {
        this.AnimeAvaliado = AnimeAvaliado;
    }
}
