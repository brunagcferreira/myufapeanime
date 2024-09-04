package br.edu.ufape.myufapeanime.myufapeanime.dto;

public class AnimeDTO {
    private Long id;
    private String nome;
    private String genero;
    private int numEpisodios;

    public AnimeDTO() {}

    public AnimeDTO(Long id, String nome, String genero, int numEpisodios) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.numEpisodios = numEpisodios;
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

    
}
