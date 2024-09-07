package br.edu.ufape.myufapeanime.myufapeanime.dto;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;

import java.util.List;

public class AnimeComAvaliacaoDTO extends AnimeDTO {
    private List<Avaliacao> avaliacoes;

    public AnimeComAvaliacaoDTO() {
        super();
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
