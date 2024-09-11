package br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao;

public class AvaliacaoUpdateDTO {
    private Long id;
    private Double nota;
    private String comentario;

    public AvaliacaoUpdateDTO () {}

    public AvaliacaoUpdateDTO(Long id, Double nota, String comentario) {
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


}
