package br.edu.ufape.myufapeanime.myufapeanime.dto.mappers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeComAvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvalicaoDoAnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;

import java.util.List;

public class AnimeMapper {
    public static Anime convertToAnimeEntity(AnimeDTO dto) {
        Anime anime = new Anime();
        anime.setId(dto.getId());
        anime.setNome(dto.getNome());
        anime.setGenero(dto.getGenero());
        anime.setNotaMedia(dto.getNotaMedia());
        anime.setNumEpisodios(dto.getNumeroEpisodios());
        return anime;
    }

    public static AnimeDTO convertToAnimeDTO(Anime anime) {
        AnimeDTO dto = new AnimeDTO();
        dto.setId(anime.getId());
        dto.setNome(anime.getNome());
        dto.setGenero(anime.getGenero());
        dto.setNotaMedia(anime.getNotaMedia());
        dto.setNumeroEpisodios(anime.getNumEpisodios());
        return dto;
    }

    public static AnimeComAvaliacaoDTO convertToAnimeComAvaliacaoDTO(Anime anime) {
        AnimeComAvaliacaoDTO dto = new AnimeComAvaliacaoDTO();
        dto.setId(anime.getId());
        dto.setNome(anime.getNome());
        dto.setGenero(anime.getGenero());
        dto.setNotaMedia(anime.getNotaMedia());
        dto.setNumeroEpisodios(anime.getNumEpisodios());

        List<AvalicaoDoAnimeDTO> avaliacaoAnimeDTOList = anime.getAvaliacoes().stream()
                .map(AvaliacaoMapper::convertToAvaliacaoDoAnimeDTO)
                .toList();


        dto.setAvaliacoes(avaliacaoAnimeDTOList);
        return dto;
    }

    public static Anime convertToAnimeEntity(AnimeComAvaliacaoDTO dto) {
        Anime anime = new Anime();
        anime.setId(dto.getId());
        anime.setNome(dto.getNome());
        anime.setGenero(dto.getGenero());
        anime.setNotaMedia(dto.getNotaMedia());
        anime.setNumEpisodios(dto.getNumeroEpisodios());
        List<AvalicaoDoAnimeDTO> avaliacaoDoAnime = anime.getAvaliacoes().stream()
                .map(AvaliacaoMapper::convertToAvaliacaoDoAnimeDTO)
                .toList();


        dto.setAvaliacoes(avaliacaoDoAnime);
        return anime;
    }


}
