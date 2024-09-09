package br.edu.ufape.myufapeanime.myufapeanime.dto.mappers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.AnimeComAvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;

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
        dto.setNumeroEpisodios(anime.getNumeroEpisodios());
        return dto;
    }

    public static AnimeComAvaliacaoDTO convertToAnimeComAvaliacaoDTO(Anime anime) {
        AnimeComAvaliacaoDTO dto = new AnimeComAvaliacaoDTO();
        dto.setId(anime.getId());
        dto.setNome(anime.getNome());
        dto.setGenero(anime.getGenero());
        dto.setNotaMedia(anime.getNotaMedia());
        dto.setNumeroEpisodios(anime.getNumeroEpisodios());
    //  dto.setAvaliacoes(anime.getAvaliacoes());
        return dto;
    }

    public static Anime convertToAnimeEntity(AnimeComAvaliacaoDTO dto) {
        Anime anime = new Anime();
        anime.setId(dto.getId());
        anime.setNome(dto.getNome());
        anime.setGenero(dto.getGenero());
        anime.setNotaMedia(dto.getNotaMedia());
        anime.setNumEpisodios(dto.getNumeroEpisodios());
    //  anime.setAvaliacoes(dto.getAvaliacoes());
        return anime;
    }

}
