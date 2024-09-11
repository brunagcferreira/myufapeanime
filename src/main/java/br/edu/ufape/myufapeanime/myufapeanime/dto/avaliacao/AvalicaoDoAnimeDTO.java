package br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao;

public class AvalicaoDoAnimeDTO {

    private Long id;
    private Long usuarioAvaliador;
    private Double nota;
    private String comentario;

    public AvalicaoDoAnimeDTO() {
    }

    public AvalicaoDoAnimeDTO(Long id, Double nota, String comentario, Long usuarioAvaliador) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.usuarioAvaliador = usuarioAvaliador;
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
}
