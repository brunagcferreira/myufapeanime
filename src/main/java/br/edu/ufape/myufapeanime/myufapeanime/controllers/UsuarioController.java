package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;
import br.edu.ufape.myufapeanime.myufapeanime.dto.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.UsuarioDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private InterfaceRepositorioUsuarios repositorioUsuarios;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> usuarios = repositorioUsuarios.findAll();
        List<UsuarioDTO> result = usuarios.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        
        List<AnimeDTO> assistindo = usuario.getAssistindo().stream()
            .map(this::convertToAnimeDTO)
            .collect(Collectors.toList());
        dto.setAssistindo(assistindo);

        return dto;
    }

    private AnimeDTO convertToAnimeDTO(Anime anime) {
        AnimeDTO dto = new AnimeDTO();
        dto.setId(anime.getId());
        dto.setNome(anime.getNome());
        dto.setGenero(anime.getGenero());
        dto.setNumEpisodios(anime.getNumEpisodios());
        return dto;
    }
}
