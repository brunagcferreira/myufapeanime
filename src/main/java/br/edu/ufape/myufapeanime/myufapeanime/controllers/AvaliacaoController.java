package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvaliacaoPeloIdDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvaliacaoUpdateDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.AvaliacaoMapper;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoDuplicadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoNotaInvalidaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> cadastrarAvaliacao(@RequestBody AvaliacaoPeloIdDTO avaliacaoPeloIdDTO) {
        try {
            Avaliacao avaliacao = convertToEntity(avaliacaoPeloIdDTO);
            Avaliacao novaAvaliacao = gerenciador.saveAvaliacao(avaliacao);
            AvaliacaoPeloIdDTO novaAvaliacaoDTO = convertToComIdDTO(novaAvaliacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacaoDTO);
        } catch (AvaliacaoNotaInvalidaException | UsuarioInexistenteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (AnimeInexistenteException | AvaliacaoDuplicadaException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    /*****  METODOS PUT *****/
    //update usuário existente
    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoUpdateDTO avaliacaoDTO)
            throws AnimeInexistenteException {
        try {
            //Avaliacao antigaAvaliacao = gerenciador.findByIdAvaliacao(id);
            Avaliacao avaliacao = convertToEntityUpdate(avaliacaoDTO);
            avaliacao.setId(id);
            Avaliacao avaliacaoAtualizado = gerenciador.updateAvaliacao(avaliacao);
            AvaliacaoPeloIdDTO avaliacaoAtualizadoDTO = convertToComIdDTO(avaliacaoAtualizado);
            return ResponseEntity.ok(avaliacaoAtualizadoDTO);
        } catch (AvaliacaoNotaInvalidaException | AvaliacaoInexistenteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

    /*****  METODO DELETE Avaliacao *****/
    //apagar usuario por id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteAvaliacao(@PathVariable Long id) throws AvaliacaoInexistenteException {
        try {
            gerenciador.deleteAvaliacao(id);
            return ResponseEntity.noContent().build();
        } catch (AvaliacaoInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

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
    public ResponseEntity<List<AvaliacaoDTO>> findById(@PathVariable Long id) {
        List<Avaliacao> avaliacao = gerenciador.findAllAvaliacao();
        List<AvaliacaoDTO> result = avaliacao.stream()
                .map(this::convertToDTO)
                .filter(AvaliacaoComIdDTO -> AvaliacaoComIdDTO.getId().equals(id))
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }


    //listar todas as Avaliações de um anime específico
    @GetMapping("/list/anime/{id}")
    public ResponseEntity<List<AvaliacaoPeloIdDTO>> findAll(@PathVariable Long id) {
        List<Avaliacao> avaliacao = gerenciador.findAllAvaliacao();
        List<AvaliacaoPeloIdDTO> result = avaliacao.stream()
                .map(this::convertToComIdDTO)
                .filter(AvaliacaoPeloIdDTO -> AvaliacaoPeloIdDTO.getAnimeAvaliado().equals(id))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    private AvaliacaoPeloIdDTO convertToComIdDTO(Avaliacao avaliacao) {
        return AvaliacaoMapper.convertToComIdDTO(avaliacao);
    }

    private AvaliacaoDTO convertToDTO(Avaliacao avaliacao) {
        return AvaliacaoMapper.convertToDTO(avaliacao);
    }

    private Avaliacao convertToEntity(AvaliacaoPeloIdDTO avaliacaoDTO) throws AnimeInexistenteException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setUsuarioAvaliador(avaliacaoDTO.getUsuarioAvaliador());
        avaliacao.setAnime(gerenciador.findByIdAnime(avaliacaoDTO.getAnimeAvaliado()));

        return avaliacao;
    }

    private Avaliacao convertToEntityUpdate(AvaliacaoUpdateDTO avaliacaoDTO) throws AnimeInexistenteException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setId(avaliacaoDTO.getId());

        return avaliacao;
    }
}


