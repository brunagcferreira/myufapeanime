package br.edu.ufape.myufapeanime.myufapeanime.dto.mappers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioComAvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioComSenhaDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioLoginDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;

import java.util.List;

public class UsuarioMapper {
    //converte Usuario em UsuarioDTO

    public static UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }

    //converte UsuarioDTO em Usuario
    public static Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        return usuario;
    }

    //converte UsuarioDTO em Usuario com senha
    public static Usuario convertToEntityPassword(UsuarioComSenhaDTO dto) {
        Usuario usuario = convertToEntity(dto);
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

    //converte UsuarioDTO em Adm
    public static Adm convertToAdm(UsuarioComSenhaDTO dto) {
        Adm usuario = new Adm();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

    //converte Usuario em UsuarioComAvaliacaoDTO
    public static UsuarioComAvaliacaoDTO convertToAvaliacaoDTO(Usuario usuario) {
        UsuarioComAvaliacaoDTO dto = new UsuarioComAvaliacaoDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        return dto;
    }

    // converte Usuario em UsuarioLoginDTO
    public static UsuarioLoginDTO convertToUsuarioComAvaliacaoDTO(Usuario usuario) {
        UsuarioLoginDTO dto = new UsuarioLoginDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());

        //converter as listas do anime para o Anime DTO para evitar loop
        List<AnimeDTO> assistindo = usuario.getAssistindo().stream()
                .map(AnimeMapper::convertToAnimeDTO)
                .toList();

        List<AnimeDTO> completo = usuario.getCompleto().stream()
                .map(AnimeMapper::convertToAnimeDTO)
                .toList();

        List<AnimeDTO> queroAssistir = usuario.getQueroAssistir().stream()
                .map(AnimeMapper::convertToAnimeDTO)
                .toList();

        dto.setAssistindo(assistindo);
        dto.setCompleto(completo);
        dto.setQueroAssistir(queroAssistir);
        return dto;
    }
}
