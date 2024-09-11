package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeComAvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.AnimeMapper;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.AnimeMapper.*;


@RestController
@RequestMapping("/anime")
public class AnimeController {
    @Autowired
    private GerenciadorAnimes gerenciadorAnimes;

    // Cadastrar um novo anime
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarAnime(@RequestBody AnimeDTO animeDTO) {
        try {
            // Converte o DTO para a entidade Anime
            Anime anime = convertToAnimeEntity(animeDTO);
            Anime novoAnime = gerenciadorAnimes.cadastrarAnime(anime);

            // Retorna o novo anime convertido de volta para DTO
            AnimeDTO novoAnimeDTO = convertToAnimeDTO(novoAnime);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAnimeDTO);
        } catch (AnimeDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // HTTP 409
        } catch (NumeroDeEpisodiosInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // HTTP 400
        }
    }

    // Listar todos os animes
    @GetMapping("/list")
    public ResponseEntity<List<AnimeDTO>> listarAnimes() {
        List<Anime> animes = gerenciadorAnimes.listarAnimes();

        // Converter lista de Anime para lista de AnimeDTO
        List<AnimeDTO> animeDTOs = animes.stream()
                .map(AnimeMapper::convertToAnimeDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(animeDTOs);
    }
    
    // Listar anime por nome'
    @GetMapping("/list/{nome}")
    public ResponseEntity<List<AnimeDTO>> listarAnimesPorNome(@PathVariable String nome) {
        List<Anime> animes = gerenciadorAnimes.findByNomeAnime(nome);

        // Converter lista de Anime para lista de AnimeDTO
        List<AnimeDTO> animeDTOs = animes.stream()
                .map(AnimeMapper::convertToAnimeDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(animeDTOs);
    }
    

    // Buscar um anime por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarAnime(@PathVariable Long id) {
        try {
            Anime anime = gerenciadorAnimes.findByIdAnime(id);
            AnimeComAvaliacaoDTO animeAvaliacaoDTO = convertToAnimeComAvaliacaoDTO(anime);
            return ResponseEntity.ok(animeAvaliacaoDTO);
        } catch (AnimeInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // HTTP 404
        }

        // TODO: implementar visualizar também as avaliações do anime
    }

    // Atualizar um anime
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarAnime(@PathVariable Long id, @RequestBody AnimeDTO animeDTO) {
        try {
            Anime animeAtualizado = convertToAnimeEntity(animeDTO);
            Anime anime = gerenciadorAnimes.atualizarAnime(id, animeAtualizado);
            AnimeDTO animeAtualizadoDTO = convertToAnimeDTO(anime);
            return ResponseEntity.ok(animeAtualizadoDTO);
        } catch (AnimeInexistenteException | AnimeDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // HTTP 404
        }
    }

    // Deletar um anime
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarAnime(@PathVariable Long id) {
        try {
            gerenciadorAnimes.deletarAnime(id);
            return ResponseEntity.noContent().build(); // HTTP 204
        } catch (AnimeInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // HTTP 404
        }
    }
}



