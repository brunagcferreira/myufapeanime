package br.edu.ufape.myufapeanime.myufapeanime.dto.mappers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.AvaliacaoComIdDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.AvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;

public class AvaliacaoMapper {

    //converte AvaliacaoComIdDTO em Avaliacao
    public static Avaliacao convertToEntity(AvaliacaoComIdDTO avaliacaoDTO) throws AnimeInexistenteException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setUsuarioAvaliador(avaliacaoDTO.getUsuarioAvaliador());
        avaliacao.setAnime(gerenciador.findByIdAnime(avaliacaoDTO.getAnimeAvaliado()));

        return avaliacao;
    }

    //converte Avaliacao para AvaliacaoComIdDTO
    public static AvaliacaoComIdDTO convertToComIdDTO(Avaliacao avaliacao) {
        AvaliacaoComIdDTO dto = new AvaliacaoComIdDTO();
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
        dto.setAnimeAvaliado(avaliacao.getAnime());

        return dto;
    }


}
