package br.edu.ufape.myufapeanime.myufapeanime.dto.anime;

import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvalicaoDoAnimeDTO;

import java.util.List;

public class AnimeComAvaliacaoDTO extends AnimeDTO {
    private List<AvalicaoDoAnimeDTO> avaliacoes;

    public AnimeComAvaliacaoDTO() {
        super();
    }

    public List<AvalicaoDoAnimeDTO> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvalicaoDoAnimeDTO> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
