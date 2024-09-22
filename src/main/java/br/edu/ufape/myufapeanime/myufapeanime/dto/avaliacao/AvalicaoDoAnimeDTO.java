package br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao;

public class AvalicaoDoAnimeDTO {

    private Long id;
    private String nomeUsuario;
    private Double nota;
    private String comentario;

    public AvalicaoDoAnimeDTO() {
    }

    public AvalicaoDoAnimeDTO(Long id, Double nota, String comentario, String usuarioAvaliador) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.nomeUsuario = usuarioAvaliador;
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

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
