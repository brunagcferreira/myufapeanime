package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.*;
import br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.AnimeMapper;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoDuplicadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoNotaInvalidaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private GerenciadorAnimes gerenciador;

    @Autowired
    private InterfaceRepositorioAnimes animeRepository;


    /*****  METODO POST Avaliacao  *****/
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarAvaliacao(@RequestBody AvaliacaoComIdDTO avaliacaoComIdDTO) {
        try {
            Avaliacao avaliacao = convertToEntity(avaliacaoComIdDTO);
            Avaliacao novaAvaliacao = gerenciador.saveAvaliacao(avaliacao);
            AvaliacaoComIdDTO novaAvaliacaoDTO = convertToComIdDTO(novaAvaliacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacaoDTO);
        } catch (AvaliacaoNotaInvalidaException | UsuarioInexistenteException e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (AnimeInexistenteException | AvaliacaoDuplicadaException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    /*****  METODOS PUT *****/
    //update usuário existente
    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoComIdDTO avaliacaoDTO) throws AnimeInexistenteException {
        try {
            Optional<Avaliacao> antigaAvaliacao = gerenciador.findByIdAvaliacao(id);
            Avaliacao avaliacao = convertToEntity(avaliacaoDTO);
            avaliacao.setId(id);
            Avaliacao avaliacaoAtualizado = gerenciador.updateAvaliacao(avaliacao, antigaAvaliacao);
            AvaliacaoComIdDTO avaliacaoAtualizadoDTO = convertToComIdDTO(avaliacaoAtualizado);
            return ResponseEntity.ok(avaliacaoAtualizadoDTO);
        } catch (AvaliacaoNotaInvalidaException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

    /*****  METODO DELETE Avaliacao *****/
    //apagar usuario por id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteAvaliacao(@PathVariable Long id) {

        gerenciador.deleteAvaliacao(id);
        return ResponseEntity.noContent().build();

    }

    /*****  METODO GET Avaliacao *****/

    //listar todas as Avaliações
    @GetMapping("/list")
    public ResponseEntity<List<AvaliacaoDTO>> findAll() {
        List<Avaliacao> avaliacao = gerenciador.findAllAvaliacao();
        List<AvaliacaoDTO> result = avaliacao.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    //lista uma Avaliação
    @GetMapping("/list/{id}")
    public ResponseEntity<List<AvaliacaoDTO>> findById(@PathVariable Long id)  {
        List<Avaliacao> avaliacao = gerenciador.findAllAvaliacao();
        List<AvaliacaoDTO> result = avaliacao.stream()
                .map(this::convertToDTO)
                .filter(AvaliacaoComIdDTO -> AvaliacaoComIdDTO.getId().equals(id))
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }


    //listar todas as Avaliações de um anime específico
    @GetMapping("/list/anime/{id}")
    public ResponseEntity<List<AvaliacaoComIdDTO>> findAll(@PathVariable Long id) {
        List<Avaliacao> avaliacao = gerenciador.findAllAvaliacao();
        List<AvaliacaoComIdDTO> result = avaliacao.stream()
                .map(this::convertToComIdDTO)
                .filter(AvaliacaoComIdDTO -> AvaliacaoComIdDTO.getAnimeAvaliado().equals(id))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    //converte AvaliacaoComIdDTO em Avaliacao
    private Avaliacao convertToEntity(AvaliacaoComIdDTO avaliacaoDTO) throws AnimeInexistenteException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setUsuarioAvaliador(avaliacaoDTO.getUsuarioAvaliador());
        avaliacao.setAnime(gerenciador.findByIdAnime(avaliacaoDTO.getAnimeAvaliado()));

        return avaliacao;
    }

    //converte Avaliacao para AvaliacaoComIdDTO
    private AvaliacaoComIdDTO convertToComIdDTO(Avaliacao avaliacao) {
        AvaliacaoComIdDTO dto = new AvaliacaoComIdDTO();
        dto.setId(avaliacao.getId());
        dto.setNota(avaliacao.getNota());
        dto.setComentario(avaliacao.getComentario());
        dto.setUsuarioAvaliador(avaliacao.getUsuarioAvaliador());
        dto.setAnimeAvaliado(avaliacao.getAnime().getId());

        return dto;
    }

    //converte Avaliacao AvaliacaoDTO
    private AvaliacaoDTO convertToDTO(Avaliacao avaliacao) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setId(avaliacao.getId());
        dto.setNota(avaliacao.getNota());
        dto.setComentario(avaliacao.getComentario());
        dto.setUsuarioAvaliador(avaliacao.getUsuarioAvaliador());
        dto.setAnimeAvaliado(avaliacao.getAnime());

        return dto;
    }
}


