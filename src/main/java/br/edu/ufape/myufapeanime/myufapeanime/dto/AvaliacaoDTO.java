package br.edu.ufape.myufapeanime.myufapeanime.dto;

public class AvaliacaoDTO {

    private String nomeAvaliador;
    private Double nota;
    private String comentario;
    
    public AvaliacaoDTO(Double nota, String comentario, String nomeAvaliador) {
        this.nota = nota;
        this.comentario = comentario;
        this.nomeAvaliador = nomeAvaliador;
    }

    public AvaliacaoDTO() {}

    public String getNomeAvaliador() {
        return nomeAvaliador;
    }

    public void setNomeAvaliador(String nomeAvaliador) {
        this.nomeAvaliador = nomeAvaliador;
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
    
}
