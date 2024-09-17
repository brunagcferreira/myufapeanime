package br.edu.ufape.myufapeanime.myufapeanime.negocio.basica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import jakarta.persistence.*;

import static java.lang.Math.max;

/**
 * Classe que representa um anime no sistema. Um anime possui atributos como nome,
 * gênero, número de episódios, pontuação, avaliações totais e uma nota média
 * calculada com base nas avaliações recebidas, pela fórmula:
 *
 * Nota geral = pontuação / avaliaçõesTotais
 *
 * Esta classe é utilizada para armazenar as informações de cada anime e
 * gerenciar as avaliações feitas pelos usuários, permitindo calcular a nota média
 * do anime com base nas avaliações totais e pontuação.
 *
 * Relacionamentos:
 * - Um anime pode possuir várias avaliações associadas.
 *
 * Funcionalidades:
 * - Cálculo da nota média do anime com base na pontuação e no número de avaliações;
 * - Armazenamento e gerenciamento de informações como nome, gênero e número de episódios.
 *
 * @author VictorAlexandre
 */


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

    // Mapeamento do relacionamento com Avaliação, para quando um anime for deletado existir um efeito cascata
    @OneToMany(mappedBy = "anime", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Avaliacao> avaliacoes;

    public Anime() {
    }

    //Fazer também um metodo que calcula a nota média do anime
    public Anime(String nome, String genero, int numEpisodios) {
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

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        //para evitar ser menor que 0
        this.pontuacao = max(0, pontuacao);
        calcularNotaMedia();
    }

    public long getAvaliacoesTotais() {
        return avaliacoesTotais;
    }

    public void setAvaliacoesTotais(Long avaliacoesTotais) {
        this.avaliacoesTotais = avaliacoesTotais;
        calcularNotaMedia();
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    private void calcularNotaMedia() {
        if (avaliacoesTotais == 0) {
            notaMedia = 0.0;
            return;
        }

        BigDecimal resultado = new BigDecimal(pontuacao / avaliacoesTotais);
        resultado = resultado.setScale(2, RoundingMode.HALF_UP); // Limita para 2 casas decimais
        
        notaMedia = resultado.doubleValue();
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
}

