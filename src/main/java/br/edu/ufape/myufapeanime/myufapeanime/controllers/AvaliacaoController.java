package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.AvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.UsuarioDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.UsuarioResponse;
import br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.AnimeMapper;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;


import java.util.List;
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
    public ResponseEntity<Object> cadastrarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {

            Avaliacao avaliacao = convertToEntity(avaliacaoDTO);
            Avaliacao novaAvaliacao = gerenciador.saveAvaliacao(avaliacao);

            AvaliacaoDTO novaAvaliacaoDTO = convertToDTO(novaAvaliacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacaoDTO);
        }

    /*****  METODOS PUT *****/
    //update usuário existente
    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoDTO avaliacaoDTO) {

            Avaliacao avaliacao = convertToEntity(avaliacaoDTO);
            avaliacao.setId(id);
            Avaliacao avaliacaoAtualizado = gerenciador.updateAvaliacao(avaliacao);
            AvaliacaoDTO avaliacaoAtualizadoDTO = convertToDTO(avaliacaoAtualizado);


        return ResponseEntity.ok(avaliacaoAtualizadoDTO);
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



    //converte AvaliacaoDTO em Avaliacao
    private Avaliacao convertToEntity(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setUsuarioAvaliador(avaliacaoDTO.getUsuarioAvaliador());
        avaliacao.setAnime(avaliacaoDTO.getAnimeAvaliado());

        return avaliacao;
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


