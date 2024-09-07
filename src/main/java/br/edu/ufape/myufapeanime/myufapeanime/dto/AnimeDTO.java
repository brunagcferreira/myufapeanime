package br.edu.ufape.myufapeanime.myufapeanime.dto;

import java.util.List;

public class AnimeDTO {
    private Long id;
    private String nome;
    private String genero;
    private int numEpisodios;
    private Double notaMedia;
    private List<AvaliacaoDTO> avaliacoes;

    public AnimeDTO() {}

    public AnimeDTO(String nome, String genero, int numEpisodios, Double notaMedia, List<AvaliacaoDTO> avaliacoes) {
        this.nome = nome;
        this.genero = genero;
        this.numEpisodios = numEpisodios;
        this.notaMedia = notaMedia;
        this.avaliacoes = avaliacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumEpisodios() {
        return numEpisodios;
    }

    public void setNumEpisodios(int numEpisodios) {
        this.numEpisodios = numEpisodios;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public List<AvaliacaoDTO> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoDTO> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    
}
