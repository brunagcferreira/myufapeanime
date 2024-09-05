package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufape.myufapeanime.myufapeanime.dto.AvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAvaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAvaliacoes;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private CadastroAvaliacao cadastroAvaliacao;
    @Autowired
    private InterfaceRepositorioAvaliacoes repositorioAvaliacoes;

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> findAll() {
        List<Avaliacao> avaliacao = repositorioAvaliacoes.findAll();//fachada
        List<AvaliacaoDTO> result = avaliacao.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    private AvaliacaoDTO convertToDTO(Avaliacao avaliacao) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setId(avaliacao.getId());
        dto.setNota(avaliacao.getNota());
        dto.setComentario(avaliacao.getComentario());
        dto.setUsuarioAvaliador(avaliacao.getUsuarioAvaliador());
        dto.setAnimeAvaliado(avaliacao.getAnime().getId());
        return dto;
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao novaAvaliacao = cadastroAvaliacao.salvarAvaliacao(avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacao);
    }

}
