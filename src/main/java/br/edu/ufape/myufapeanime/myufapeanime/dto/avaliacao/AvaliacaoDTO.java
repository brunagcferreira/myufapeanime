package br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao;

import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeDTO;

public class AvaliacaoDTO {

    private Long id;
    private Double nota;
    private String comentario;
    private Long usuarioAvaliador;
    private AnimeDTO animeAvaliado;


    public AvaliacaoDTO() {}

    public AvaliacaoDTO(Long id, Double nota, String comentario, Long usuarioAvaliador, AnimeDTO animeAvaliado) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.usuarioAvaliador = usuarioAvaliador;
        this.animeAvaliado = animeAvaliado;
    }

    public AvaliacaoDTO(Long id, Double nota, String comentario) {
        this.id = id;
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

    public Long getUsuarioAvaliador() {
        return usuarioAvaliador;
    }

    public void setUsuarioAvaliador(Long usuarioAvaliador) {
        this.usuarioAvaliador = usuarioAvaliador;
    }

    public AnimeDTO getAnimeAvaliado() {
        return animeAvaliado;  // Retorna o objeto Anime
    }

    public void setAnimeAvaliado(AnimeDTO animeAvaliado) {
        this.animeAvaliado = animeAvaliado;  // Define o objeto Anime
    }

}
