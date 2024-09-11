package br.edu.ufape.myufapeanime.myufapeanime.dto.anime;


public class AnimeDTO {
    private Long id;
    private String nome;
    private String genero;
    private int numeroEpisodios;

    // Estatísticas que devem ser inicializadas
    private double notaMedia;

    // Construtor padrão
    public AnimeDTO() {
    }

    // Construtor completo (se necessário)
    public AnimeDTO(String nome, String genero, int numeroEpisodios) {
        this.nome = nome;
        this.genero = genero;
        this.numeroEpisodios = numeroEpisodios;
        this.notaMedia = 0.0;
    }

    // Getters e setters
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

    public int getNumeroEpisodios() {
        return numeroEpisodios;
    }

    public void setNumeroEpisodios(int numeroEpisodios) {
        this.numeroEpisodios = numeroEpisodios;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

