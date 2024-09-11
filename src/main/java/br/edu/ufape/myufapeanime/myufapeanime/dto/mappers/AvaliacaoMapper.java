package br.edu.ufape.myufapeanime.myufapeanime.dto.mappers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvaliacaoPeloIdDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvalicaoDoAnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;

public class AvaliacaoMapper {

    private GerenciadorAnimes gerenciador;


    //converte Avaliacao para AvaliacaoComIdDTO
    public static AvaliacaoPeloIdDTO convertToComIdDTO(Avaliacao avaliacao) {
        AvaliacaoPeloIdDTO dto = new AvaliacaoPeloIdDTO();
        dto.setId(avaliacao.getId());
        dto.setNota(avaliacao.getNota());
        dto.setComentario(avaliacao.getComentario());
        dto.setUsuarioAvaliador(avaliacao.getUsuarioAvaliador());

        dto.setAnimeAvaliado(avaliacao.getAnime().getId());

        return dto;
    }

    //converte avaliacao em avaliacaoDTO
    public static AvaliacaoDTO convertToDTO(Avaliacao avaliacao) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setId(avaliacao.getId());
        dto.setNota(avaliacao.getNota());
        dto.setComentario(avaliacao.getComentario());
        dto.setUsuarioAvaliador(avaliacao.getUsuarioAvaliador());

        AnimeDTO animeDaAvaliacao = AnimeMapper.convertToAnimeDTO(avaliacao.getAnime());
        dto.setAnimeAvaliado(animeDaAvaliacao);

        return dto;
    }

    // converte avaliacoes em avaliacoesDTO
    public static AvalicaoDoAnimeDTO convertToAvaliacaoDoAnimeDTO(Avaliacao avaliacao) {
        AvalicaoDoAnimeDTO dto = new AvalicaoDoAnimeDTO();
        dto.setId(avaliacao.getId());
        dto.setNota(avaliacao.getNota());
        dto.setComentario(avaliacao.getComentario());
        dto.setUsuarioAvaliador(avaliacao.getUsuarioAvaliador());

        return dto;
    }
}
