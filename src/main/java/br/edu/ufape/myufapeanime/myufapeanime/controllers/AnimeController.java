package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufape.myufapeanime.myufapeanime.dto.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    
    @Autowired
    private GerenciadorAnimes gerenciador;

    @GetMapping("/{id}")
    public ResponseEntity<AnimeDTO> getAnimeComAvaliacoes(@PathVariable Long id) {
        try {
            AnimeDTO animeDTO = gerenciador.buscarAnimeComAvaliacoes(id);
            return ResponseEntity.ok(animeDTO);
        } catch (AnimeInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

