package br.edu.ufape.myufapeanime.myufapeanime.negocio.basica;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_animes")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String genero;
    private int numEpisodios;
    private Double pontuacao;
    private Long avaliacoesTotais;
    private Double notaMedia;
    @OneToMany(mappedBy = "anime")
    private List<Avaliacao> avaliacoes;

    public Anime() {}

    //Fazer também um metodo que calcula a nota média do anime
    public Anime(String nome, String genero, int numEpisodios) {
        this.nome = nome;
        this.genero = genero;
        this.numEpisodios = numEpisodios;
        this.pontuacao = 0.0;
        this.avaliacoesTotais = 0L;
        this.notaMedia = 0.0;
        this.avaliacoes = null;
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

    public int getNumeroEpisodios() {
        return numEpisodios;
    }

    public void setNumEpisodios(int numEpisodios) {
        this.numEpisodios = numEpisodios;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public long getAvaliacoesTotais() {
        return avaliacoesTotais;
    }

    public void setAvaliacoesTotais(Long avaliacoesTotais) {
        this.avaliacoesTotais = avaliacoesTotais;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

}
