package br.edu.ufape.myufapeanime.myufapeanime.dto;

public class AvaliacaoDTO {

    private Long id;
    private Double nota;
    private String comentario;
    private Long usuarioAvaliador;
    private Long animeAvaliado;

    public AvaliacaoDTO() {}

    public AvaliacaoDTO(Double nota, String comentario, Long usuarioAvaliador, Long animeAvaliado) {
        this.nota = nota;
        this.comentario = comentario;
        this.usuarioAvaliador = usuarioAvaliador;
        this.animeAvaliado = animeAvaliado;
    }

    public AvaliacaoDTO(Long id, Double nota, String comentario) {
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
        return animeAvaliado;
    }

    public void setAnimeAvaliado(Long animeAvaliado) {
        this.animeAvaliado = animeAvaliado;
    }

    
}
