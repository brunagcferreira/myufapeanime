package br.edu.ufape.myufapeanime.myufapeanime.dto.mappers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;

public class UsuarioMapper {
    //converte Anime em AnimeDTO
    public static AnimeDTO convertToAnimeDTO(Anime anime) {
        AnimeDTO dto = new AnimeDTO();
        dto.setId(anime.getId());
        dto.setNome(anime.getNome());
        dto.setGenero(anime.getGenero());
        dto.setNumeroEpisodios(anime.getNumeroEpisodios());
        return dto;
    }

    //converte Usuario em UsuarioDTO
    public static UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        return dto;
    }

    //converte UsuarioDTO em Usuario
    public static Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }
    public static Adm convertToAdm(UsuarioDTO dto) {
        Adm usuario = new Adm();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

}
